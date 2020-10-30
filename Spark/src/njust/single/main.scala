package njust.single

import java.io.{File, FileInputStream}
import java.util.Calendar

import org.apache.commons.io.IOUtils
import njust.tools.JTool
import njust.sad.sad

import scala.collection.immutable.ListMap

object main {
  def main(args: Array[String]): Unit = {

    //    val pixels = 350 * 350
    val bands = 224
    val pixels = 128 * 128
    //    val data = new FileInputStream(new File("/home/hadoop/data/c350bip/c350bip"))
    //    val dd = JTool.BtoFBip(IOUtils.toByteArray(data), 350 * 350, bands, 2)

    var starttime1 = Calendar.getInstance().getTimeInMillis.toInt

    val data = new FileInputStream(new File("/home/hadoop/c350bip/gaussian"))
    val dd = JTool.BtoFBip(IOUtils.toByteArray(data), pixels, bands, 2)
    var starttime2 = Calendar.getInstance().getTimeInMillis.toInt
    println("data read time:" + (starttime2 - starttime1))
    val mat = JTool.createMat(10000 , bands)
    val svd = mat.mkString(",")

    val maxMin = JTool.calcMaxMin(dd, mat, 0)
    // count pixel index
    var index = JTool.countIndex(maxMin, pixels)
    var endmemberWithCount: scala.collection.mutable.ListMap[Int,(Array[Double], Int)] = scala.collection.mutable.ListMap()
    var k = 0
    for( i <- 0 until index.size){
      if(index(i) >  20) {
        endmemberWithCount += (k -> (dd(i), index(i)))
        k = k +1
      }
    }

    for( i <- 0 until endmemberWithCount.size){
      for( j <- i + 1 until endmemberWithCount.size){
        if(endmemberWithCount.get(i).get._2 != 0 && sad.sad(endmemberWithCount.get(i).get._1, endmemberWithCount.get(j).get._1)< 0.01){
          //
          if(endmemberWithCount.get(i).get._2 > endmemberWithCount.get(j).get._2){
            endmemberWithCount(i) = (endmemberWithCount.get(i).get._1,endmemberWithCount.get(i).get._2 + endmemberWithCount.get(j).get._2)
            endmemberWithCount(j) = (endmemberWithCount.get(j).get._1,0)
          }else{
            endmemberWithCount(j) = (endmemberWithCount.get(j).get._1,endmemberWithCount.get(i).get._2 + endmemberWithCount.get(j).get._2)
            endmemberWithCount(i) = (endmemberWithCount.get(i).get._1,0)
          }
        }
      }
    }

    val resWithDataSorted = ListMap(endmemberWithCount.toSeq.sortBy(_._2._2).reverse:_*)

    resWithDataSorted.foreach(x=> {
      print(x._2._2 + " ")
      x._2._1.foreach( y => print(y + ", "))
      println()
    })

    var endtime = Calendar.getInstance().getTimeInMillis.toInt
    println("calu time: "+ (endtime - starttime2))
    println("all time: "+ (endtime - starttime1))
  }
}
