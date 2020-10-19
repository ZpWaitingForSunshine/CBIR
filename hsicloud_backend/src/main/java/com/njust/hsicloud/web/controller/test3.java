package com.njust.hsicloud.web.controller;

import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.core.assist.gantchart.displayGanttChart;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.util.Map;
import java.util.HashMap;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;

public class test3 {

    public static void main(String[] args) throws Exception {
        try {
            String resultPath="/home/hadoop/data/bgn.xml";
            Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","/home/hadoop/Desktop/quick4j-master/lib/CloudsimCuckoo.jar","/home/hadoop/data/result.xml",resultPath,"4","1"});
            ps.waitFor();
            java.io.InputStream is=ps.getInputStream();
            byte b[]=new byte[is.available()];
            is.read(b,0,b.length);
            System.out.println(new String(b));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
//
//
//    private HashMap<String,String> jobMap = new HashMap<String, String>();
//
//    //提交加载输入法词库任务
//    public static Map<String, Object> loadingDict() throws Exception {
//
//        //使用帮助类HttpClients创建CloseableHttpClient对象.
//        CloseableHttpClient client = HttpClients.createDefault();
//        //HTTP请求类型创建HttpPost实例
//        HttpPost post = new HttpPost("http://master:6066/v1/submissions/create");
//        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
//        post.setHeader("Content-Type", "application/json;charset=UTF-8");
//        //组织数据
//        String content = "{\"action\":\"CreateSubmissionRequest\",\"appArgs\":[\"local\",\"jobname\",\"hdfs://10.10.10.96:9000/CBIRDemo/rice/\",\"T17-09-38_R\",\"4000\",\"2500\",\"1500\",\"0\",\"/home/hadoop/12112.jpg\",\"/home/hadoop/22112.jpg\",\"2\",\"/home/hadoop/Desktop\"],\"appResource\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"clientSparkVersion\":\"2.3.3\",\"environmentVariables\":{\"SPARK_ENV_LOADED\":\"1\"},\"mainClass\":\"yk.DetectionMain\",\"sparkProperties\":{\"spark.jars\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"spark.driver.supervise\":\"false\",\"spark.app.name\":\"MyJob\",\"spark.eventLog.enabled\":\"true\",\"spark.submit.deployMode\":\"cluster\",\"spark.master\":\"spark://10.10.10.96:6066\"}}";
//        String content1 = "{\"action\":\"CreateSubmissionRequest\",\"appArgs\":[\"local\",\"jobname\",\"hdfs://10.10.10.96:9000/CBIRDemo/rice/\",\"T17-09-38_R\",\"4000\",\"2500\",\"1500\",\"0\",\"/home/hadoop/12112.jpg\",\"/home/hadoop/22112.jpg\",\"2\",\"/home/hadoop/Desktop\"],\"appResource\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"clientSparkVersion\":\"2.3.3\",\"environmentVariables\":{\"SPARK_ENV_LOADED\":\"1\"},\"mainClass\":\"yk.DetectionMain\",\"sparkProperties\":{\"spark.jars\":\"hdfs://10.10.10.96:9000/HSIRead.jar\",\"spark.driver.supervise\":\"false\",\"spark.app.name\":\"MyJob\",\"spark.eventLog.enabled\":\"true\",\"spark.submit.deployMode\":\"cluster\",\"spark.master\":\"spark://10.10.10.96:6066\"}}";
//        String content2 = "{\"action\":\"CreateSubmissionRequest\",\"appArgs\":[\"local\",\"jobname\",\"hdfs://10.10.10.96:9000/CBIRDemo/rice/\",\"T17-09-38_R\",\"4000\",\"2500\",\"1500\",\"0\",\"/home/hadoop/12112.jpg\",\"/home/hadoop/22112.jpg\",\"2\",\"/home/hadoop/Desktop/\"],\"appResource\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"clientSparkVersion\":\"2.3.3\",\"environmentVariables\":{\"SPARK_ENV_LOADED\":\"1\"},\"mainClass\":\"yk.DetectionMain\",\"sparkProperties\":{\"spark.jars\":\"hdfs://10.10.10.96:9000/HSIRead.jar,hdfs://10.10.10.96:9000/ricedetection.jar\",\"spark.driver.supervise\":\"false\",\"spark.app.name\":\"MyJob\",\"spark.eventLog.enabled\":\"true\",\"spark.submit.deployMode\":\"cluster\",\"spark.master\":\"spark://10.10.10.96:6066\"}}";
//        StringEntity se = new StringEntity(content2, "utf-8");
//
//        //设置编码格式
//        se.setContentEncoding("UTF-8");
//        //设置数据类型
//        se.setContentType("application/json");
//        //对于POST请求,把请求体填充进HttpPost实体.
//        post.setEntity(se);
//        //通过执行HttpPost请求获取CloseableHttpResponse实例 ,从此CloseableHttpResponse实例中获取状态码,错误信息,以及响应页面等等.
//        CloseableHttpResponse response = client.execute(post);
//        //通过HttpResponse接口的getEntity方法返回响应信息，并进行相应的处理
//        HttpEntity entity = response.getEntity();
//        String resData = EntityUtils.toString(response.getEntity(), "utf-8");
//        System.out.println(resData);
//        //最后关闭HttpClient资源.
//        response.close();
//        client.close();
//
////        SparkResultEntity sparkResultEntity = JSON.parseObject(resData, SparkResultEntity.class);
////        if (sparkResultEntity.isSuccess()) {
////            jobMap.put(sparkResultEntity.getSubmissionId(), "init");
////        }
////
////        dataMap.put("resData", resData);
////        return CommonUtil.successResult(dataMap);
//        return null;
//    }
//}
