package njust.ppi

import njust.ppi.PPI


import sys.process._
/**
 * Created by jingle on 2015/6/10.
 */
object Extraction {
  /**
    *
    * @param args
    *             args(0): filename
    *             args(1): masterIP
    *             args(2): hdfsIP
    *             args(3): the numbers of random vector
    */
  def main(args: Array[String]): Unit = {


    val PP = new PPI(args(0), args(1), args(2), args(3).toInt)
    PP.start(1,2)
  }
}