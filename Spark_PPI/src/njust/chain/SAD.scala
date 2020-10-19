package njust.chain

import java.util
import java.util.Properties

import breeze.linalg._
import njust.model.Image
import org.apache.spark.SparkContext
import njust.tools.Operation
import org.apache.spark.sql.SQLContext
class SAD(val image: Image, val ppiResult:Array[Array[Double]], val url:String, val spark:SparkContext){

  def execute(): Array[(Long, Double, String)] = {
    val sqlContext = new SQLContext(spark)
    //定义表名
    val table="specdata"
    //properties
    val properties=new Properties()
    properties.setProperty("user","root")
    properties.setProperty("password","admin")
    val df = sqlContext.read.jdbc(url,table,properties)

//    Operation.singleCZV2()

    val wavelength = image.getWavelength // wavelength
    val spectralInfoBroadcast = spark.broadcast(wavelength, ppiResult)
    val specIDSet = df.rdd.map(pair => {
      // each pair is a item of specdata
      var wavelength = spectralInfoBroadcast.value._1.replaceAll("\"","").replaceAll("\r\n","").replaceAll(" ","")
      var spectralSelected = spectralInfoBroadcast.value._2 // endmembers spectral
      var compareResult = new Array[(Long, Double, String)](spectralSelected.length)
//      var compareResultID = new Array[Long](spectralSelected.length)
      for(i <- 0 until compareResult.length){
        compareResult(i) = (0, Integer.MAX_VALUE,"")
      }

      for(i <- 0 until spectralSelected.length){
        val map1 = new java.util.HashMap[String, String]()
        val map2 = new java.util.HashMap[String, String]()

        val speclibArray1 = pair.get(1).toString.replaceAll(" ", "").replaceAll("\r\n", "").split(",")
        val speclibArray2 = pair.get(2).toString.split(",")
        for (j <- 0 until speclibArray1.length){
          map1.put(speclibArray1(j), speclibArray2(j))
        }

        val resArray1 = wavelength.split(",")
        val resArray2 = spectralSelected(i).mkString(",").replaceAll(" ","").split(",")
        for (j <- 0 until resArray1.length){
          map2.put(resArray1(j), resArray2(j))
        }

        val sadScore = Math.abs( Operation.singleCZV2(map1, map2))
        if(sadScore < compareResult(i)._2){
          compareResult(i) = (pair.get(0).toString.toLong, sadScore, pair.get(2).toString)
        }
      }
      compareResult
    }).reduce((x,y) => {
      var compareResult = new Array[(Long, Double, String)](x.length)
      for (i <- 0 until x.length){
        if(x(i)._2 < y(i)._2){
          compareResult(i) = x(i)
        }else{
          compareResult(i) = y(i)
        }
      }
      compareResult
    })
    specIDSet
  }
}
