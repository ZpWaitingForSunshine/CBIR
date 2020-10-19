package com.njust.hsicloud.web.controller;

import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.core.entity.JSONResult;
import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.Envi;
import com.njust.hsicloud.web.model.JobSubmitRequest;
import com.njust.hsicloud.web.service.ComponentsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.spark.deploy.SparkSubmit;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.CountDownLatch;


@Controller
@RequestMapping("/components")
public class ComponentsController {
    @Autowired
    private ComponentsService componentsService;

    @GetMapping
    @ResponseBody
    public List<Components> getComponentsByParam(Map<String,Object> param ){
        return componentsService.getAllComponents();
    }

    /**
     * get image list
     *
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getAll() {
//                System.out.println("hello");
        JSONObject jResult = new JSONObject();
        List<Components> list = componentsService.getAllComponents();
        jResult.put("data", list );
        jResult.put("code", 200);
        jResult.put("total", list.size());
        return jResult.toString();
    }
    @RequestMapping(value = "/getaldetail", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getaldetail(@RequestParam("cid") Integer cid) {
        JSONObject jResult = new JSONObject();
        List<Components> list = componentsService.getAllComponents();
        jResult.put("data", list );
        jResult.put("code", 200);
        jResult.put("total", list.size());
        return jResult.toString();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getById(@RequestParam("cid") Integer cid){
//                System.out.println("hello");
        JSONObject jResult = new JSONObject();
        Components list = componentsService.getOne(cid);
        jResult.put("data", list );
        jResult.put("code", 200);
//                jResult.put("total", list.size());
        return jResult.toString();
    }

//    @RequestMapping(value = "/test",method = RequestMethod.POST)
//    public void test(@RequestParam Map<String,Object> map){
//        System.out.println("6666");
//
//        Set<String>set=map.keySet();
//        Iterator it=set.iterator();
//        while(it.hasNext()){
//            String key=(String)it.next();
//            String val= (String) map.get(key);
//            System.out.println(key+":"+val);
//        }
//
//    }

    @RequestMapping(value = "/exec", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String exec(HttpServletRequest request, @RequestParam("params")  String json) throws IOException, InterruptedException {

        JSONObject applicationJson = JSONObject.fromObject(json);
        String componentName = applicationJson.getString("name");
        String jobname = "";
        String orginal = "";
        String result = "";
        Map<String, String> map = new HashMap<>();
        // projectServerpath for storing
        String projectServerPath = request.getScheme() + "://"+request.getServerName()+":" +
                request.getServerPort() + request.getContextPath() + "/app/hdr/";
        String path = request.getSession().getServletContext().getRealPath("/app/hdr/");

        System.out.println("的"+componentName);
        if(componentName.equals("大米检测")){
//                JSONObject input = JSONObject.fromObject(applicationJson.getString("input"));
            JSONArray components = applicationJson.getJSONArray("components");
            JSONObject rice = JSONObject.fromObject(components.get(0));
            JSONArray args = JSONArray.fromObject(rice.getString("input"));
            String filename="", good="2500", normal="1500", bad="4000", background = "0";
            for(int i = 0; i < args.size(); i ++){
                JSONObject temp = JSONObject.fromObject(args.get(i));
//                    JSONArray temp1 = JSONArray.fromObject(temp.getString("input"));
                switch (temp.getString("en")){
                    case "inFileName": filename = temp.getString("value");break;
                    case "good": good = temp.getString("value");break;
                    case "normal": normal = temp.getString("value");break;
                    case "bad": bad = temp.getString("value");break;
                    case "background": background = temp.getString("value");break;
                }
            }
            System.out.println(path);

            jobname = filename + System.currentTimeMillis(); // job's name
            orginal = filename + "_Original.jpg";
            result = filename + "_Result.jpg";
            CloseableHttpClient client = HttpClients.createDefault();
            //HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
            //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            //组织数据
            JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
            jobSubmitRequest.setAction("CreateSubmissionRequest");
            jobSubmitRequest.setAppResource("hdfs://master:9000/ricedetection.jar");
            jobSubmitRequest.setClientSparkVersion("2.3.3");
            List<String> params = new ArrayList<>();
            String[] argss = {"local", "riceDetection", "hdfs://master:9000/CBIRDemo/rice/", filename, bad, good, normal,
                    "0", "/home/hadoop/" + orginal, "/home/hadoop/" + result, background, path};
            params.addAll(Arrays.asList(argss)); // add args to the args list for spark
            jobSubmitRequest.setAppArgs(params);
            Map<String,String> environment = new HashMap<>();
            environment.put("SPARK_ENV_LOADED","1");
            jobSubmitRequest.setEnvironmentVariables(environment);
            jobSubmitRequest.setMainClass("yk.DetectionMain");
            Map<String, String> sparkProperties = new HashMap<>();
            sparkProperties.put("spark.jars", "hdfs://master:9000/HSIRead.jar,hdfs://master:9000/ricedetection.jar");
            sparkProperties.put("spark.driver.supervise","false");
            sparkProperties.put("spark.app.name",jobname);
//                sparkProperties.put("spark.eventLog.enabled","true");
            sparkProperties.put("spark.submit.deployMode", "cluster");
            sparkProperties.put("spark.master", "spark://master:6066");
            jobSubmitRequest.setSparkProperties(sparkProperties);
            String content = JSON.toJSONString(jobSubmitRequest);
//                String content = "{\"action\":\"CreateSubmissionRequest\",\"appArgs\":[\"local\",\"jobname\",\"hdfs://10.10.10.96:9000/CBIRDemo/rice/\",\"T17-09-38_R\",\"4000\",\"2500\",\"1500\",\"0\",\"/home/hadoop/12112.jpg\",\"/home/hadoop/22112.jpg\",\"2\",\"/home/hadoop/Desktop\"],\"appResource\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"clientSparkVersion\":\"2.3.3\",\"environmentVariables\":{\"SPARK_ENV_LOADED\":\"1\"},\"mainClass\":\"yk.DetectionMain\",\"sparkProperties\":{\"spark.jars\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"spark.driver.supervise\":\"false\",\"spark.app.name\":\"MyJob\",\"spark.eventLog.enabled\":\"true\",\"spark.submit.deployMode\":\"cluster\",\"spark.master\":\"spark://10.10.10.96:6066\"}}";
//                String content1 = "{\"action\":\"CreateSubmissionRequest\",\"appArgs\":[\"local\",\"jobname\",\"hdfs://10.10.10.96:9000/CBIRDemo/rice/\",\"T17-09-38_R\",\"4000\",\"2500\",\"1500\",\"0\",\"/home/hadoop/12112.jpg\",\"/home/hadoop/22112.jpg\",\"2\",\"/home/hadoop/Desktop\"],\"appResource\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"clientSparkVersion\":\"2.3.3\",\"environmentVariables\":{\"SPARK_ENV_LOADED\":\"1\"},\"mainClass\":\"yk.DetectionMain\",\"sparkProperties\":{\"spark.jars\":\"hdfs://10.10.10.96:9000/HSIRead.jar\",\"spark.driver.supervise\":\"false\",\"spark.app.name\":\"MyJob\",\"spark.eventLog.enabled\":\"true\",\"spark.submit.deployMode\":\"cluster\",\"spark.master\":\"spark://10.10.10.96:6066\"}}";
//                String content2 = "{\"action\":\"CreateSubmissionRequest\",\"appArgs\":[\"local\",\"jobname\",\"hdfs://10.10.10.96:9000/CBIRDemo/rice/\",\"T17-09-38_R\",\"4000\",\"2500\",\"1500\",\"0\",\"/home/hadoop/12112.jpg\",\"/home/hadoop/22112.jpg\",\"2\",\"/home/hadoop/Desktop/\"],\"appResource\":\"hdfs://10.10.10.96:9000/ricedetection.jar\",\"clientSparkVersion\":\"2.3.3\",\"environmentVariables\":{\"SPARK_ENV_LOADED\":\"1\"},\"mainClass\":\"yk.DetectionMain\",\"sparkProperties\":{\"spark.jars\":\"hdfs://10.10.10.96:9000/HSIRead.jar,hdfs://10.10.10.96:9000/ricedetection.jar\",\"spark.driver.supervise\":\"false\",\"spark.app.name\":\"MyJob\",\"spark.eventLog.enabled\":\"true\",\"spark.submit.deployMode\":\"cluster\",\"spark.master\":\"spark://10.10.10.96:6066\"}}";
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
            HttpEntity entity = response.getEntity();
            String resData = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(resData);
            //最后关闭HttpClient资源.
            response.close();
            client.close();

            map.put("original", projectServerPath + orginal);
            map.put("result",projectServerPath +  result);
            map.put("jobname", jobname);
            map.put("spark",resData);

            JSONObject jResult = new JSONObject();
            jResult.put("code", 200);
            jResult.put("data",map);
            return jResult.toString();
        }else if(componentName.equals("纯净像元指数算法")){

            JSONArray components = applicationJson.getJSONArray("components");
            JSONObject PPI = JSONObject.fromObject(components.get(0));
            JSONArray args = JSONArray.fromObject(PPI.getString("input"));
            String filename="", endmembers ="2500",inFilePath="",outPiexlPath="",picPiexl="";
            for(int i = 0; i < args.size(); i ++){
                JSONObject temp = JSONObject.fromObject(args.get(i));
//                    JSONArray temp1 = JSONArray.fromObject(temp.getString("input"));
                switch (temp.getString("en")){
                    case "inFileName": filename = temp.getString("value");break;
                    case "inFilePath":inFilePath=temp.getString("value");break;
                    case "randomNum": endmembers = temp.getString("value");break;

                    case "outPiexlPath":outPiexlPath=temp.getString("value");break;
                    case "picPixel":picPiexl=temp.getString("value");break;
                }
            }

            jobname = filename + System.currentTimeMillis(); // job's name
            result = filename + "_Result.jpg";
            String resulttxt = filename + "_Result.txt";
            CloseableHttpClient client = HttpClients.createDefault();
            //HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
            //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            //组织数据

            JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
            jobSubmitRequest.setAction("CreateSubmissionRequest");
            jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars/Spark_PPI.jar");
            jobSubmitRequest.setClientSparkVersion("2.3.3");

            List<String> params = new ArrayList<>();
            String[] argss = {filename,  inFilePath, endmembers,
                    outPiexlPath, picPiexl};

            params.addAll(Arrays.asList(argss)); // add args to the args list for spark
            jobSubmitRequest.setAppArgs(params);

            Map<String,String> environment = new HashMap<>();
            environment.put("SPARK_ENV_LOADED","1");
            jobSubmitRequest.setEnvironmentVariables(environment);

            jobSubmitRequest.setMainClass("jingle.ppi.Extraction");

            Map<String, String> sparkProperties = new HashMap<>();
            sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Spark_PPI.jar");
            sparkProperties.put("spark.driver.supervise","false");
            sparkProperties.put("spark.app.name",jobname);
            sparkProperties.put("spark.submit.deployMode", "cluster");
            sparkProperties.put("spark.master", "spark://10.10.10.105:6066");

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
            HttpEntity entity = response.getEntity();
            String resData = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(resData);
            //最后关闭HttpClient资源.
            response.close();
            client.close();


            List<String> names = new ArrayList<>();
            List<Integer> spectrals = new ArrayList<>();


//                map.put("original", projectServerPath + orginal);
            map.put("result",projectServerPath +  result);
            map.put("resulttxt", projectServerPath + resulttxt);
            map.put("filename", projectServerPath + filename + ".jpg");
            map.put("jobname", jobname);
            map.put("spark",resData);

            JSONObject jResult = new JSONObject();
            jResult.put("code", 200);
            jResult.put("data",map);
            return jResult.toString();


        }else if(componentName.equals("光谱特征匹配算法")){

            JSONArray components = applicationJson.getJSONArray("components");
            JSONObject pca = JSONObject.fromObject(components.get(0));
            JSONArray args = JSONArray.fromObject(pca.getString("input"));
            String filename="", filename2="",inFilePath="",inLibraryName="",inLibraryPath="",inResultPath="",outPath="";
            for(int i = 0; i < args.size(); i ++){
                JSONObject temp = JSONObject.fromObject(args.get(i));
                switch (temp.getString("en")){
                    case "inFileName": filename = temp.getString("value");break;
                    case "inFilePath":inFilePath=temp.getString("value");break;
                    case "inLibraryName":inLibraryName=temp.getString("value");break;
                    case "inLibraryPath":inLibraryPath=temp.getString("value");break;
                    case "inResultPath":inResultPath=temp.getString("value");break;
                    case "outPath":outPath=temp.getString("value");break;
                }
            }
            System.out.println(filename+"***"+filename2);

            jobname = filename + System.currentTimeMillis(); // job's name



            CloseableHttpClient client = HttpClients.createDefault();
            //HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
            //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            //组织数据

            JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
            jobSubmitRequest.setAction("CreateSubmissionRequest");
            jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars/spack_sad.jar");
            jobSubmitRequest.setClientSparkVersion("2.3.3");

            List<String> params = new ArrayList<>();
            //???????????????????????
            String[] argss = {filename,inFilePath,inLibraryPath,inLibraryName,inResultPath,"0","191",
                    outPath,"local[*]"};

            params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
            jobSubmitRequest.setAppArgs(params);

            Map<String,String> environment = new HashMap<>();
            environment.put("SPARK_ENV_LOADED","1");
            jobSubmitRequest.setEnvironmentVariables(environment);

            jobSubmitRequest.setMainClass("yk.sad.SADMain");

            Map<String, String> sparkProperties = new HashMap<>();
            sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/spack_sad.jar");//???
            sparkProperties.put("spark.driver.supervise","false");
            sparkProperties.put("spark.app.name",jobname);
            sparkProperties.put("spark.submit.deployMode", "cluster");
            sparkProperties.put("spark.master", "spark://10.10.10.105:6066");

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

            map.put("original", projectServerPath + orginal);
            map.put("result",projectServerPath +  result);
            map.put("jobname", jobname);
            map.put("spark",resData);

            JSONObject jResult = new JSONObject();
            jResult.put("code", 200);
            jResult.put("data",map);
            return jResult.toString();
        }else if(componentName.equals("N-Finder算法")){
            JSONArray components = applicationJson.getJSONArray("components");
            JSONObject pca = JSONObject.fromObject(components.get(0));
            JSONArray args = JSONArray.fromObject(pca.getString("input"));
            String filename="", endMemberNum="",filePath="",outPiexlPath="";
            for(int i = 0; i < args.size(); i ++){
                JSONObject temp = JSONObject.fromObject(args.get(i));
                switch (temp.getString("en")){
                    case "inFilePath": filePath= temp.getString("value");break;
                    case "inFileName": filename = temp.getString("value");break;
                    case "pixNum":endMemberNum=temp.getString("value");break;
                    case "outPiexlPath":outPiexlPath=temp.getString("value");break;

                }
            }
            System.out.println(filename+"***"+endMemberNum);

            jobname = filename + System.currentTimeMillis(); // job's name



            CloseableHttpClient client = HttpClients.createDefault();
            //HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
            //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            //组织数据

            JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
            jobSubmitRequest.setAction("CreateSubmissionRequest");
            jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars/Unmixing_NFinder.jar");
            jobSubmitRequest.setClientSparkVersion("2.3.3");

            List<String> params = new ArrayList<>();
            //???????????????????????
            String[] argss = {filename,filePath,endMemberNum,outPiexlPath, "local[*]"};

            params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
            jobSubmitRequest.setAppArgs(params);

            Map<String,String> environment = new HashMap<>();
            environment.put("SPARK_ENV_LOADED","1");
            jobSubmitRequest.setEnvironmentVariables(environment);

            jobSubmitRequest.setMainClass("lyl.unmixing.Unmixing");

            Map<String, String> sparkProperties = new HashMap<>();
            sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars/Unmixing_NFinder.jar");//???
            sparkProperties.put("spark.driver.supervise","false");
            sparkProperties.put("spark.app.name",jobname);
            sparkProperties.put("spark.submit.deployMode", "cluster");
            sparkProperties.put("spark.master", "spark://10.10.10.105:6066");

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

            map.put("original", projectServerPath + orginal);
            map.put("result",projectServerPath +  result);
            map.put("jobname", jobname);
            map.put("spark",resData);

            JSONObject jResult = new JSONObject();
            jResult.put("code", 200);
            jResult.put("data",map);
            return jResult.toString();
        }else if(componentName.equals("K-Means聚类算法")){
            JSONArray components = applicationJson.getJSONArray("components");
            JSONObject pca = JSONObject.fromObject(components.get(0));
            JSONArray args = JSONArray.fromObject(pca.getString("input"));
            String filename="", k="",inFilePath="",classCenter="",classResultPic="";
            for(int i = 0; i < args.size(); i ++){
                JSONObject temp = JSONObject.fromObject(args.get(i));
                switch (temp.getString("en")){
                    case "inFilePath": inFilePath = temp.getString("value");break;
                    case "inFileName": filename = temp.getString("value");break;
                    case "classNum":k=temp.getString("value");break;
                    case "classCenter":classCenter=temp.getString("value");break;
                    case "classResultPic":classResultPic=temp.getString("value");break;
                }
            }
            System.out.println(filename+"***"+k);

            jobname = filename + System.currentTimeMillis(); // job's name



            CloseableHttpClient client = HttpClients.createDefault();
            //HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
            //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            //组织数据

            JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
            jobSubmitRequest.setAction("CreateSubmissionRequest");
            jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars/kmeans.jar");
            jobSubmitRequest.setClientSparkVersion("2.3.3");

            List<String> params = new ArrayList<>();
            //???????????????????????
            String[] argss = {filename,inFilePath,k,classCenter, classResultPic,"local[*]"};

            params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
            jobSubmitRequest.setAppArgs(params);

            Map<String,String> environment = new HashMap<>();
            environment.put("SPARK_ENV_LOADED","1");
            jobSubmitRequest.setEnvironmentVariables(environment);

            jobSubmitRequest.setMainClass("yk.MainLoopSpark");

            Map<String, String> sparkProperties = new HashMap<>();
            sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/kmeans.jar");
            sparkProperties.put("spark.driver.supervise","false");
            sparkProperties.put("spark.app.name",jobname);
            sparkProperties.put("spark.submit.deployMode", "cluster");
            sparkProperties.put("spark.master", "spark://10.10.10.105:6066");

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

            map.put("original", projectServerPath + orginal);
            map.put("result",projectServerPath +  result);
            map.put("jobname", jobname);
            map.put("spark",resData);

            JSONObject jResult = new JSONObject();
            jResult.put("code", 200);
            jResult.put("data",map);
            return jResult.toString();
        }else if(componentName.equals("主成分分析法")){
            JSONArray components = applicationJson.getJSONArray("components");
            JSONObject pca = JSONObject.fromObject(components.get(0));
            JSONArray args = JSONArray.fromObject(pca.getString("input"));
            String filename="", dim="",inFilePath="",outResultPath="";
            for(int i = 0; i < args.size(); i ++){
                JSONObject temp = JSONObject.fromObject(args.get(i));
                switch (temp.getString("en")){
                    case "inFileName": filename = temp.getString("value");break;
                    case "inFilePath": inFilePath = temp.getString("value");break;
                    case "demensionNum":dim=temp.getString("value");break;
                    case "outResultPath":outResultPath=temp.getString("value");break;
                }
            }
            System.out.println(filename+"***"+dim);

            jobname = filename + System.currentTimeMillis(); // job's name



            CloseableHttpClient client = HttpClients.createDefault();
            //HTTP请求类型创建HttpPost实例
            HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
            //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
            post.setHeader("Content-Type", "application/json;charset=UTF-8");
            //组织数据

            JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
            jobSubmitRequest.setAction("CreateSubmissionRequest");
            jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars/Pcaspack.jar");
            jobSubmitRequest.setClientSparkVersion("2.3.3");

            List<String> params = new ArrayList<>();
            //???????????????????????
            String[] argss = {inFilePath,filename,dim,outResultPath};

            params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
            jobSubmitRequest.setAppArgs(params);

            Map<String,String> environment = new HashMap<>();
            environment.put("SPARK_ENV_LOADED","1");
            jobSubmitRequest.setEnvironmentVariables(environment);

            jobSubmitRequest.setMainClass("lyl.pca.PCAMain");

            Map<String, String> sparkProperties = new HashMap<>();
            sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars/Pcaspack.jar");
            sparkProperties.put("spark.driver.supervise","false");
            sparkProperties.put("spark.app.name",jobname);
            sparkProperties.put("spark.submit.deployMode", "cluster");
            sparkProperties.put("spark.master", "spark://10.10.10.105:6066");

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

            map.put("original", projectServerPath + orginal);
            map.put("result",projectServerPath +  result);
            map.put("jobname", jobname);
            map.put("spark",resData);

            JSONObject jResult = new JSONObject();
            jResult.put("code", 200);
            jResult.put("data",map);
            return jResult.toString();
        }

        JSONObject jResult = new JSONObject();
        jResult.put("code", 200);
        jResult.put("data",map);
//            jResult.put("result",re)
        return jResult.toString();
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String jobState(@RequestParam("params")  String submissionId) throws Exception {
        Map<String,Object> dataMap = new HashMap<String, Object>();
//
//        //使用帮助类HttpClients创建CloseableHttpClient对象.
        CloseableHttpClient client = HttpClients.createDefault();
//        //HTTP请求类型创建HttpPost实例
        HttpGet get = new HttpGet("http://master:6066/v1/submissions/status/"+submissionId);
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
        JSONObject jResult = new JSONObject();
        jResult.put("data",resData);
        System.out.println(resData);
        jResult.put("code",200);

        return jResult.toString();
//        SparkResultEntity sparkResultEntity = JSON.parseObject(resData,SparkResultEntity.class);
//

//        jobMap.put(sparkResultEntity.getSubmissionId(),sparkResultEntity.getDriverState());
//
//        dataMap.put("resData",sparkResultEntity.getDriverState());
//        return CommonUtil.successResult(dataMap);
    }
}
