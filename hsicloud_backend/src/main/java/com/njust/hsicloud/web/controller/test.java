package com.njust.hsicloud.web.controller;
import com.github.ywilkof.sparkrestclient.FailedSparkRequestException;
import com.github.ywilkof.sparkrestclient.SparkRestClient;
import org.apache.hadoop.fs.FileSystem;
import ch.ethz.ssh2.Connection;
import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.core.assist.runc.RemoteShellTool;
import com.njust.hsicloud.web.model.JobSubmitRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.apache.http.HttpEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.deploy.SparkSubmit;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.apache.spark.scheduler.SparkListener;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.omg.CORBA.Request;
import org.springframework.web.bind.annotation.RequestParam;
import sun.net.www.http.HttpClient;

import javax.net.ssl.SSLEngine;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.io.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.rest.CreateSubmissionResponse;
import org.apache.spark.deploy.rest.RestSubmissionClient;
import org.apache.spark.deploy.rest.SubmissionStatusResponse;

import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
public class test {


public static void main(String[] args) throws FailedSparkRequestException {

    String[] argss = {"http://10.10.10.110:8081", "49",  "hdfs://10.10.10.110:9000", "jdbc:mysql://10.10.10.116:3306/hsicloud"};

    SparkRestClient sparkRestClient = SparkRestClient.builder()
            .masterHost("10.10.10.110")
            .sparkVersion("2.3.3")
            .build();
    final String submissionId = sparkRestClient.prepareJobSubmit()
            .appName("MySparkJob!")
            .appResource("hdfs://10.10.10.110:9000/PPI_SAD_SCLS.jar")
            .appArgs(Arrays.asList(argss.clone()))
            .mainClass("njust.chain.index")

            .submit();
    System.out.println(submissionId);
    // 根据给定的URI和用户名，访问hdfs的配置参数
//    Configuration conf = new Configuration();
//
//    // 允许访问datanode时使用域名，默认使用IP
//    conf.set("dfs.client.use.datanode.hostname","true");
//
//    // Hadoop的用户名,应当与远端启动hdfs进程的用户名相同，或者根据hdfs的安全设置自行配置
//    String hdfsUserName = "root";
//
//    URI hdfsUri = null;
//    try {
//        // NameNode的访问路径
//        hdfsUri = new URI("hdfs://10.10.10.110:9000");
//    } catch (URISyntaxException e) {
//        e.printStackTrace();
//    }
//
//    try {
//        // 创建FileSystem对象,并调用copyToLocalFile函数将hdfs中的一个文件拷贝到本地目录
//        FileSystem fs = FileSystem.get(hdfsUri, conf, hdfsUserName);
//        fs.copyToLocalFile(new Path("./"),
//                new Path("/tmp"));
//    } catch (IOException e) {
//        e.printStackTrace();
//    } catch (InterruptedException e) {
//        e.printStackTrace();
//    }
//    try {
//        String localSrc = "/root/a.txt";
//        String dst = "hdfs://10.10.10.110:9000/5";
//        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));
//        Configuration conf = new Configuration();
//        conf.set("dfs.client.use.datanode.hostname","true");
//
//        FileSystem fs = FileSystem.get(URI.create(dst), conf);
//        OutputStream out = fs.create(new Path(dst), new Progressable() {
//            public void progress() {
//                System.out.print(".");
//            }
//        });
//        IOUtils.copyBytes(in, out, 4096, true);
//        System.out.println("success");
//
//    } catch (Exception e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//    }
}
}




