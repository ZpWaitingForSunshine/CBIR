package njust.ppi

import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}
import njust.tools.{HSIInputFormat, HSIRecordReader, HSIhdr}
import njust.sad.sad
import njust.ppi.JTool
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapred.KeyValueTextInputFormat

/**
 *
 */
class PPI(val filename:String, val masterIP:String, val hdfsIP:String, val randomVectorNumber:Int){

  val header = new HSIhdr(filename, hdfsIP)

  val bands = header.getBands // bands
  val row = header.getRow     // row
  val col = header.getCol     // col
  val len = col * row         // len

  def start( a:Int, b:Int ) : Void = {

    // init spark
    val home = System.getenv("SPARK_HOME") //获得环境变量
    val sparkConf = new SparkConf().setAppName("ppi")
      //.setSparkHome(home)
//      .setMaster("local[4]")
    val spark = new SparkContext(sparkConf)
    //  val spark = new SparkContext(conf)
    //val spark = new SparkContext(conf)

    //广播变量是通过调用SparkContext.broadcast(v)方法从变量v创建的。广播变量是一个v的封装器，它的值可以通过调用value方法获得。
    //在广播变量被创建后，它应该在集群运行的任何函数中，代替v值被调用，从而v值不需要被再次传递到这些结点上。另外，对象v不能在广播后修改，
    // 这样可以保证所有结点的收到的都是一模一样的广播值。

    val t1 = System.currentTimeMillis()
    val mat = JTool.createMat(randomVectorNumber, bands)

    val bconf = spark.broadcast(row, col, bands, header.getDatatype, header.getInter.toLowerCase(), mat)
    //    val broadcastss = spark.broadcast(Array(1, 2, 3))
    val file = spark.newAPIHadoopFile(hdfsIP + filename, classOf[HSIInputFormat], classOf[Integer], classOf[Array[Byte]])
    val loadtime = System.currentTimeMillis()
    println("load data time" + (loadtime - t1))
    println("create mat ok!")
    /*
        读取高光谱数据，并用hsidata作为数据存储rdd，并cache
         */
    val hsidata = file.map(pair => {

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
      val key: Long = pair._1  / (bands * datasize) * len

      val pixel = len / (datasize * bands)
      val fdata = bconf.value._5 match { //header.getInter.toLowerCase()
        case "bil" => JTool.BtoFBil(data, pixel, col, bands, datasize)
        case "bip" => JTool.BtoFBip(data, pixel, bands, datasize)
        case _ => {
          println("不支持的interleave格式!")
          sys.exit(-1)
        }
      }
      println("key" + key)
      (key, fdata)
    }).cache()

//    val hsikey = hsidata.map(x=>{
//      x._1
//    }).collect()
//    hsikey.foreach(x=> println(x))
    val readtime = System.currentTimeMillis()
    println("read data ok!" + (readtime - loadtime))

    // count max & min
    val maxMin = hsidata.map(pair => {
      val maxMin = JTool.calcMaxMin(pair._2, bconf.value._6, pair._1)
      maxMin
    }).reduce((x, y) => {
      //计算最终的最大最小值
      val mM = JTool.calFinalMaxMin(x, y) // position
      mM
    })

    val caluMaxmintime =  System.currentTimeMillis()
    println("calu MaxMin ok!" + (caluMaxmintime - readtime))

    //
    val res1 = spark.parallelize(maxMin).map(x=>{
      (x(0) -> 1)
    }).reduceByKey(_+_).collect()

    val res2 = spark.parallelize(maxMin).map(x=>{
      (x(1) -> 1)
    }).reduceByKey(_+_).collect()

    val res =  res1.toMap ++ res2.toMap.map(t => t._1 -> (t._2 + res1.toMap.getOrElse(t._1, 0)))

    val sortRes = res.toSeq.sortBy(_._2).reverse
    sortRes.foreach(x => {
      if(x._2 > 80)
        println(x)
    })





//    //
//    //  // count pixel index
//    println("len " + len)
//    val index = JTool.countIndex(maxMin, len)
//
//    val counttime =  System.currentTimeMillis()
//    println("count MaxMin ok!" + (counttime - caluMaxmintime))
//    println("count pixel index ok!")
    null

  }

}


