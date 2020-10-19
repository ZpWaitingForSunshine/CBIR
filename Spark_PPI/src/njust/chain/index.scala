package njust.chain

import njust.tools.Request
import org.apache.spark.{SparkConf, SparkContext}



object index {
  /***
    *
    * @param args
    *             0: server url
    *             1: image id
    *             2: hdfs baseurl
    *
    */
  def main(args: Array[String]): Unit = {
//    import scala.io.Source
//    val url= args(0) +  "/rest/image/getimage?id=" + args(1);
    var request  = new Request
    val image = request.fetchImage(args(1).toInt, args(0))
    image.setHdfsurl(args(2) + image.getHdfsurl.replaceAll("\"",""))

    val home = System.getenv("SPARK_HOME") //获得环境变量
    val sparkConf = new SparkConf().setAppName("unmixing")
    //.setSparkHome(home)
//          .setMaster("local[4]")
    val spark = new SparkContext(sparkConf)
      println("SPARK OK!")

    val ppi = new PPI(image, 1000, spark)
    val ppiResult = ppi.execute();
      println("PPI OK!")

    val sad = new SAD(image, ppiResult, args(3), spark)
    val sadResult = sad.execute()
      println("SAD OK!")

    val scls = new SCLS(image, spark, sadResult)
    val sclsResult = scls.execute()
      println("SCLS OK!")

    request.insertAbundances(sclsResult, args(1).toInt, args(0))
      println("Insert OK!")







   
      //
  }
}
