package com.njust.rsCloud.web.controller;

import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkSubmit;

public class test {

    public static void main(String[] argv){
//        String[] jars = new String[1];
//        jars[0] = ""
//        SparkConf conf = new SparkConf().setAppName("appp").setJars(" ");
//        conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
//        conf.set("spark.kryo.registrator","xurtx.hsi.spark.KyroRegistratorImpl");
        String[] arg0=new String[]{
                "--master","spark://master:7077",
//                "--deploy-mode","client",
                "--name","rice",
                "--class","Scala_Test",
//                "--executor-memory","8G",
//                "--total-executor-cores","4",
//                "--executor-cores","2",

                "/home/hadoop/sparkprogram/wordcount/out/artifacts/wordcount_jar/wordcount.jar",
                "/home/hadoop/start.sh","/home/hadoop/test1/hdr/1.txt",
//                "local[*]",
//                "rice",
//                "hdfs://10.10.10.96:9000/CBIRDemo/rice/",
//                "T17-15-45",
//                "4000","2000","1600","100","/home/hadoop/1.jpg","/home/hadoop/2.jpg"
        };
        System.out.println("hhhh2h22");
        SparkSubmit.main(arg0);

    }
}
