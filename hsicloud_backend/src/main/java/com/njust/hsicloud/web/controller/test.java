package com.njust.hsicloud.web.controller;

import ch.ethz.ssh2.Connection;
import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.core.assist.runc.RemoteShellTool;
import com.njust.hsicloud.web.model.JobSubmitRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
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

import java.util.*;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.rest.CreateSubmissionResponse;
import org.apache.spark.deploy.rest.RestSubmissionClient;
import org.apache.spark.deploy.rest.SubmissionStatusResponse;

import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
public class test {


public static void main(String[] args) {
    test sb=new test();
    String status="";
    try{
      //  sb.submitiea();
        status=sb.jobState("driver-20200523203451-0003");
        System.out.println("dd"+status);
        JSONObject jsonObj = JSONObject.fromObject(status);
        String result= jsonObj.getString("driverState");
        System.out.println(result);
    }catch (Exception e){
        e.printStackTrace();
    }

}
    void submitpca() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost("http://10.10.10.64:6066/v1/submissions/create");
        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        //组织数据
        JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
        jobSubmitRequest.setAction("CreateSubmissionRequest");
        jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars191215/Pcaspack.jar");
        jobSubmitRequest.setClientSparkVersion("2.3.3");
        List<String> params = new ArrayList<>();
        String[] argss = {"hdfs://10.10.10.64:9000/","c350bip_2","15", "hdfs://10.10.10.79:9000/pp1216","1","jdbc:mysql://10.10.10.76:3306/test","pca",
                "http://10.10.10.79:8088/rest/uploadService/save","100","hdfs://10.10.10.79:9000/","/home/hadoop/data/chart.png"};
        params.addAll(Arrays.asList(argss)); // add args to the args list for spark
        jobSubmitRequest.setAppArgs(params);
        Map<String, String> environment = new java.util.HashMap<>();
        environment.put("SPARK_ENV_LOADED", "1");
        jobSubmitRequest.setEnvironmentVariables(environment);
        jobSubmitRequest.setMainClass("lyl.pca.PCAMain");
        Map<String, String> sparkProperties = new java.util.HashMap<>();
        sparkProperties.put("spark.driver.supervise", "false");
        sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar," +
                "hdfs://10.10.10.64:9000/jars190717/httpmime-4.5.3.jar," +
                "hdfs://10.10.10.64:9000/jars190717/jfreechart-1.0.19.jar,hdfs://10.10.10.64:9000/jars190717/jcommon-1.0.23.jar,hdfs://10.10.10.64:9000/jars191215/Pcaspack.jar");
        sparkProperties.put("spark.app.name", "pca");
        sparkProperties.put("spark.submit.deployMode", "cluster");
        sparkProperties.put("spark.master", "spark://10.10.10.64:6066");
        jobSubmitRequest.setSparkProperties(sparkProperties);
        String content = JSON.toJSONString(jobSubmitRequest);
        StringEntity se = new StringEntity(content, "utf-8");
        //设置编码格式
        se.setContentEncoding("UTF-8");
        //设置数据类型
        se.setContentType("application/json");
        //对于POST请求,把请求体填充进HttpPost实体.
        post.setEntity(se);
        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
        CloseableHttpResponse response = client.execute(post);
        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
        //   HttpEntity entity = response.getEntity();
        String resData = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(resData);
        //最后关闭HttpClient资源.
        response.close();
        client.close();
    }
void submitppi() throws IOException {
    CloseableHttpClient client = HttpClients.createDefault();
            //HTTP请求类型创建HttpPost实例
    HttpPost post = new HttpPost("http://10.10.10.64:6066/v1/submissions/create");
    //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
    post.setHeader("Content-Type", "application/json;charset=UTF-8");
    //组织数据
    JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
    jobSubmitRequest.setAction("CreateSubmissionRequest");
    jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars191215/Spark_PPI.jar");
    jobSubmitRequest.setClientSparkVersion("2.3.3");
    List<String> params = new ArrayList<>();
    String[] argss = {"c350bip_2","hdfs://10.10.10.64:9000/","13580","hdfs://10.10.10.64:9000/endmenbers11215.txt",
    "hdfs://10.10.10.64:9000/ppires11215.jpg",
    "hdfs://10.10.10.64:9000/",
    "1",
    "ppires1.txt",
    "ppires2.jpg",
    "jdbc:mysql://10.10.10.76:3306/test",
    "111",
    "http://10.10.10.79:8088/rest/uploadService/save"};
    params.addAll(Arrays.asList(argss)); // add args to the args list for spark
    jobSubmitRequest.setAppArgs(params);
    Map<String, String> environment = new java.util.HashMap<>();
    environment.put("SPARK_ENV_LOADED", "1");
    jobSubmitRequest.setEnvironmentVariables(environment);
    jobSubmitRequest.setMainClass("jingle.ppi.Extraction");
    Map<String, String> sparkProperties = new java.util.HashMap<>();
    sparkProperties.put("spark.driver.supervise", "false");
    sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190717/httpmime-4.5.3.jar,hdfs://10.10.10.64:9000/jars191215/Spark_PPI.jar");
    sparkProperties.put("spark.app.name", "PPI");
    sparkProperties.put("spark.submit.deployMode", "cluster");
    sparkProperties.put("spark.master", "spark://10.10.10.64:6066");
    jobSubmitRequest.setSparkProperties(sparkProperties);
    String content = JSON.toJSONString(jobSubmitRequest);
    StringEntity se = new StringEntity(content, "utf-8");
    //设置编码格式
    se.setContentEncoding("UTF-8");
    //设置数据类型
        se.setContentType("application/json");
        //对于POST请求,把请求体填充进HttpPost实体.
    post.setEntity(se);
    //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
    CloseableHttpResponse response = client.execute(post);
    //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
    //   HttpEntity entity = response.getEntity();
    String resData = EntityUtils.toString(response.getEntity(), "utf-8");
    System.out.println(resData);
    //最后关闭HttpClient资源.
    response.close();
    client.close();
}
    void submitiea() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost("http://10.10.10.101:6066/v1/submissions/create");
        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        //组织数据
        JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
        jobSubmitRequest.setAction("CreateSubmissionRequest");
        jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars191215/IEAscala.jar");
        jobSubmitRequest.setClientSparkVersion("2.3.3");
        List<String> params = new ArrayList<>();
        String[] argss = {"hdfs://10.10.10.64:9000/","c350bip_2","15","0.09",
                "hdfs://10.10.10.64:9000/iea523j.jpg",
                "hdfs://10.10.10.64:9000/iea523t.txt",
                "1",
                "jdbc:mysql://10.10.10.76:3306/test",
                "100",
                "hdfs://10.10.10.64:9000/",
                "http://10.10.10.79:8088/rest/uploadService/save",
                "iea523.jpg",
                "iea523.txt",
                };
        params.addAll(Arrays.asList(argss)); // add args to the args list for spark
        jobSubmitRequest.setAppArgs(params);
        Map<String, String> environment = new java.util.HashMap<>();
        environment.put("SPARK_ENV_LOADED", "1");
        jobSubmitRequest.setEnvironmentVariables(environment);
        jobSubmitRequest.setMainClass("iea.IEAMain");
        Map<String, String> sparkProperties = new java.util.HashMap<>();
        sparkProperties.put("spark.driver.supervise", "false");
        sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190717/httpmime-4.5.3.jar,hdfs://10.10.10.64:9000/jars191215/IEAscala.jar");
        sparkProperties.put("spark.app.name", "iea");
        sparkProperties.put("spark.submit.deployMode", "cluster");
        sparkProperties.put("spark.master", "spark://10.10.10.101:6066");
        jobSubmitRequest.setSparkProperties(sparkProperties);
        String content = JSON.toJSONString(jobSubmitRequest);
        StringEntity se = new StringEntity(content, "utf-8");
        //设置编码格式
        se.setContentEncoding("UTF-8");
        //设置数据类型
        se.setContentType("application/json");
        //对于POST请求,把请求体填充进HttpPost实体.
        post.setEntity(se);
        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
        CloseableHttpResponse response = client.execute(post);
        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
        //   HttpEntity entity = response.getEntity();
        String resData = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(resData);
        //最后关闭HttpClient资源.
        response.close();
        client.close();
    }
    void submitsad() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost("http://10.10.10.64:6066/v1/submissions/create");
        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        //组织数据
        JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
        jobSubmitRequest.setAction("CreateSubmissionRequest");
        jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars191215/spack_sad.jar");
        jobSubmitRequest.setClientSparkVersion("2.3.3");
        List<String> params = new ArrayList<>();
        String[] argss = {"c350bip_2", "hdfs://10.10.10.64:9000/" ,
                "hdfs://10.10.10.64:9000/" ,"usgs_min" ,"hdfs://10.10.10.64:9000/data/ppitxt.txt", "0" ,"191" ,
                "hdfs://10.10.10.64:9000/name1219.txt", "hdfs://10.10.10.64:9000/" ,"2" ,"name.txt",
                "jdbc:mysql://10.10.10.76:3306/test", "http://10.10.10.79:8088/rest/uploadService/save", "101", "hdfs://10.10.10.64:9000/" };
        params.addAll(Arrays.asList(argss)); // add args to the args list for spark
        jobSubmitRequest.setAppArgs(params);
        Map<String, String> environment = new java.util.HashMap<>();
        environment.put("SPARK_ENV_LOADED", "1");
        jobSubmitRequest.setEnvironmentVariables(environment);
        jobSubmitRequest.setMainClass("yk.sad.SADMain");
        Map<String, String> sparkProperties = new java.util.HashMap<>();
        sparkProperties.put("spark.driver.supervise", "false");
        sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190717/httpmime-4.5.3.jar,hdfs://10.10.10.64:9000/jars191215/spack_sad.jar");
        sparkProperties.put("spark.app.name", "PPI");
        sparkProperties.put("spark.submit.deployMode", "cluster");
        sparkProperties.put("spark.master", "spark://10.10.10.64:6066");
        jobSubmitRequest.setSparkProperties(sparkProperties);
        String content = JSON.toJSONString(jobSubmitRequest);
        StringEntity se = new StringEntity(content, "utf-8");
        //设置编码格式
        se.setContentEncoding("UTF-8");
        //设置数据类型
        se.setContentType("application/json");
        //对于POST请求,把请求体填充进HttpPost实体.
        post.setEntity(se);
        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
        CloseableHttpResponse response = client.execute(post);
        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
        //   HttpEntity entity = response.getEntity();
        String resData = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(resData);
        //最后关闭HttpClient资源.
        response.close();
        client.close();
    }
    String submitword() throws IOException {
        String jobname="wc";
        CloseableHttpClient client = HttpClients.createDefault();
        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost("http://10.10.10.64:6066/v1/submissions/create");
        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        //组织数据

        JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
        jobSubmitRequest.setAction("CreateSubmissionRequest");
        jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/user/wordcount.jar");
        jobSubmitRequest.setClientSparkVersion("2.3.3");

        List<String> params = new ArrayList<>();
        //???????????????????????
        String[] argss = {"hdfs://10.10.10.64:9000/eds.txt","wco.txt"};

        params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
        jobSubmitRequest.setAppArgs(params);

        Map<String, String> environment = new HashMap<>();
        environment.put("SPARK_ENV_LOADED", "1");
        jobSubmitRequest.setEnvironmentVariables(environment);

        jobSubmitRequest.setMainClass("wcmain");

        Map<String, String> sparkProperties = new HashMap<>();
        sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/user/wordcount.jar");
        sparkProperties.put("spark.driver.supervise", "false");
        sparkProperties.put("spark.app.name", jobname);
        sparkProperties.put("spark.submit.deployMode", "cluster");
        sparkProperties.put("spark.master", "spark://10.10.10.64:6066");
        jobSubmitRequest.setSparkProperties(sparkProperties);
        String content = JSON.toJSONString(jobSubmitRequest);
        StringEntity se = new StringEntity(content, "utf-8");

        //设置编码格式
        se.setContentEncoding("UTF-8");
        //设置数据类型
        se.setContentType("application/json");
        //对于POST请求,把请求体填充进HttpPost实体.
        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        post.setEntity(se);
        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
        CloseableHttpResponse response = client.execute(post);
        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
        HttpEntity entity = response.getEntity();
        String resData = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(resData);
        //最后关闭HttpClient资源.
        response.close();
        client.close();
        JSONObject jResult = new JSONObject();
        jResult.put("code", 200);
        jResult.put("data",resData);
        return jResult.toString();
    }
    String jobState(String submissionId) throws Exception {


//        //使用帮助类HttpClients创建CloseableHttpClient对象.
        CloseableHttpClient client = HttpClients.createDefault();
//        //HTTP请求类型创建HttpPost实例
        HttpGet get = new HttpGet("http://10.10.10.101:6066/v1/submissions/status/"+submissionId);
//        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        get.setHeader("Content-Type", "application/json;charset=UTF-8");
//        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
        CloseableHttpResponse response = client.execute(get);
//        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
       HttpEntity entity = response.getEntity();
        String resData = EntityUtils.toString(response.getEntity(),"utf-8");
//        //最后关闭HttpClient资源.
        response.close();
        client.close();
        return resData;
    }
}




