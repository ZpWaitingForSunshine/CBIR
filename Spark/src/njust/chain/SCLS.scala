package njust.chain

import java.util

import breeze.linalg.{Axis, DenseMatrix, DenseVector, inv, sum}
import breeze.numerics.{pow, sqrt}
import njust.model.Image
import njust.tools.{HSIInputFormat, HSIhdr, JTool}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

class SCLS(val image: Image, val spark:SparkContext, val sadResult:Array[(Long, Double, String)]) {

  val row = image.getRows     // row
  val col = image.getSamples     // col
  val len = col * row         // len
  val datatype = image.getDatatype
  val interler = image.getInterleave

  def execute(): util.HashMap[Integer, Double] = {

    val filename = image.getId.toString
    val hdfsurl = image.getHdfsurl

    // get HSI header

    //init M infomation
    val cols = sadResult.length //
    var bands = 0
    //
    val theta = 0.01

    var endmembers = new ArrayBuffer[Double]()

    val MVector = new DenseVector[Double](0)


    for ( i <- 0 until cols){
      val item = sadResult(i)._3.split(",")
      bands = item.length
      val itemDouble = item.map(x => x.toDouble).toBuffer
      endmembers ++= itemDouble
//      MVector(i *item.length to (i + 1) *item.length) := DenseVector(itemDouble)

    }

    val M = new DenseMatrix(bands, cols, endmembers.toArray)


    val ones = DenseMatrix.ones[Double](1, cols)

    val N = DenseMatrix.vertcat(M * theta ,ones)

    // broadcast
    val af =spark.broadcast(inv(N.t * N) * N.t, bands, M)
    val bconf = spark.broadcast(row, col, bands, datatype.toShort, interler)

    // init load image
    val file = spark.newAPIHadoopFile(hdfsurl, classOf[HSIInputFormat], classOf[Integer], classOf[Array[Byte]])

    // map operation
    val ratio = file.map(pair => {
      val datatype = bconf.value._4 match {
        case 2 => 2.toShort
        case 4 => 4.toShort
        case 12 => 2.toShort
        case _ => {
          println("不支持的datasize格式!");
          0.toShort
          sys.exit(-1)
        }
      }
      val data = pair._2 // classOf[Array[Byte]]
      val len = data.length // size of this partition(Byte)
      val col = bconf.value._2
      val bands = bconf.value._3
      //            val key = pair._1 / (bands * datatype) * len
      val pixel = len / (datatype * bands)
      println("pixel: " + pixel)
      val fdata = bconf.value._5 match { //header.getInter.toLowerCase()
        case "bil" => JTool.BtoFBil(data, pixel, col.toShort, bands.toShort, datatype)
        case "bip" => JTool.BtoFBip(data, pixel, bands.toShort, datatype)
        case _ => {
          println("不支持的interleave格式!")
          sys.exit(-1)
        }
      }

      // fdataMatirx is the partition data
      val fdataMatrix = DenseMatrix.tabulate(fdata.length, bands){ // rows 192 cols n
        case (i, j) => fdata(i)(j)
      }
      val ones = DenseMatrix.ones[Double](1, fdata.length)

      // each partition data plus ones for calu the abundance
      val X = DenseMatrix.vertcat(fdataMatrix.t * 0.0001 * theta, ones)

      val M = af.value._3 // endmembers set (bands * number)
      val abundance = (af.value._1 * X)
      abundance
    }).reduce((x, y)=>{
      val a = sum(x, Axis._1)
      val b = sum(y, Axis._1)
      a.toDenseMatrix + b.toDenseMatrix
    })
    val ratios = (ratio / len.toDouble)

    var map = new util.HashMap[Integer, Double]()
    for( i <- 0 until ratios.size){
      map.put(sadResult(i)._1.toInt, ratios(0, i))
    }
    map

  }
}
