package njust.chain

import java.io.{File, PrintWriter}

import njust.model.Image
import org.apache.spark.{SparkConf, SparkContext}
import njust.tools.{HSIInputFormat, HSIRecordReader, HSIhdr}
import njust.sad.sad
import njust.ppi.JTool
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapred.KeyValueTextInputFormat

/**
  *
  */
class PPI(val image:Image, val randomVectorNumber:Int, val spark:SparkContext){

  val bands = image.getBands // bands
  val row = image.getRows     // row
  val col = image.getSamples    // col
  val len = col * row         // len

  def execute(): Array[Array[Double]] = {

    //广播变量是通过调用SparkContext.broadcast(v)方法从变量v创建的。广播变量是一个v的封装器，它的值可以通过调用value方法获得。
    //在广播变量被创建后，它应该在集群运行的任何函数中，代替v值被调用，从而v值不需要被再次传递到这些结点上。另外，对象v不能在广播后修改，
    // 这样可以保证所有结点的收到的都是一模一样的广播值。

    val t1 = System.currentTimeMillis()
    val mat = JTool.createMat(randomVectorNumber, bands.toShort)

    val bconf = spark.broadcast(row, col, bands, image.getDatatype.toShort, image.getInterleave.toLowerCase(), mat)
    //    val broadcastss = spark.broadcast(Array(1, 2, 3))
    val file = spark.newAPIHadoopFile(image.getHdfsurl, classOf[HSIInputFormat], classOf[Integer], classOf[Array[Byte]])
    val loadtime = System.currentTimeMillis()

    /*
        读取高光谱数据，并用hsidata作为数据存储rdd，并cache
         */
    val hsidata = file.map(pair => {
      println(pair._1)

      val datasize = bconf.value._4 match {
        case 2 => 2.toShort
        case 4 => 4.toShort
        case 12 => 2.toShort
        case _ => {
          println("不支持的datasize格式!");
          0.toShort
          sys.exit(-1)
        }
      }
      val data = pair._2 //classOf[Array[Byte]] 数组类型数据
      val len = data.length //图像总共的大小（Byte-字节）
      val col = bconf.value._2
      val bands = bconf.value._3
      val key: Long = pair._1.toLong

      val pixel = len / (datasize * bands)
      val fdata = bconf.value._5 match { //header.getInter.toLowerCase()
        case "bil" => JTool.BtoFBil(data, pixel, col.toShort, bands.toShort, datasize)
        case "bip" => JTool.BtoFBip(data, pixel, bands.toShort, datasize)
        case _ => {
          println("不支持的interleave格式!")
          sys.exit(-1)
        }
      }
      (key, fdata)
    }).cache()

    val readtime = System.currentTimeMillis()

    // count max & min
    val maxMin = hsidata.map(pair => {
      val maxMin = JTool.calcMaxMin(pair._2, bconf.value._6, pair._1 * pair._2.length )
      maxMin
    }).reduce((x, y) => {
      //计算最终的最大最小值
      val mM = JTool.calFinalMaxMin(x, y) // position
      mM
    })

    val caluMaxmintime =  System.currentTimeMillis()

    //
    val res1 = spark.parallelize(maxMin).map(x=>{
      (x(0) -> 1)
    }).reduceByKey(_+_).collect()

    val res2 = spark.parallelize(maxMin).map(x=>{
      (x(1) -> 1)
    }).reduceByKey(_+_).collect()

    val res =  res1.toMap ++ res2.toMap.map(t => t._1 -> (t._2 + res1.toMap.getOrElse(t._1, 0)))

    val sortRes = res.toSeq.sortBy(_._2).reverse // (offset, count)
    sortRes.foreach(x => {
      if(x._2 > 80)
        println(x)
    })

    val endmemberSorted = spark.broadcast(sortRes)

    val pure = hsidata.flatMap(pair => {
      val endmembers = endmemberSorted.value
      for(e <- endmembers if e._1 >= pair._1 * pair._2.length && e._1 < (pair._1 + 1) * pair._2.length)
        yield pair._2((e._1 - pair._1 * pair._2.length).toInt)
    }).collect()


    var pure1 = pure.map(x => {
      x.map( y => y/10000)
    })

    pure1
  }

}


