package com.njust.hsicloud.web.controller;

import java.io.*;
import java.util.*;

import com.njust.hsicloud.web.model.StateModel;
import com.njust.hsicloud.web.service.ComponentsService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.dom4j.*;
import org.dom4j.io.*;
import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.JobSubmitRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.avro.generic.GenericData;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.njust.hsicloud.core.entity.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import com.njust.hsicloud.web.model.nodeModel;
import java.io.File;
import java.io.FileOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;
@Controller
@RequestMapping("/xml")
public class AnalysisDagController {
    @Autowired
    private ComponentsService componentsService;

    @GetMapping
    @ResponseBody
    public List<Components> getComponentsByParam(Map<String, Object> param) {
        return componentsService.getAllComponents();
    }
    ArrayList<nodeModel> nodeList = new ArrayList<nodeModel>();
    JSONArray allResult = new JSONArray();
   // Map<String,> map = new HashMap<>();
    @RequestMapping(value = "/analysis", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody

    String analysisXML(HttpServletRequest request, @RequestParam("list") String json) {

        try {
            File f = new File("/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/data/result/result.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            Element nodes = root.element("nodes");
            for (Iterator node = nodes.elementIterator("node"); node.hasNext(); ) {
                nodeModel sambleNode = new nodeModel();
                Element foo = (Element) node.next();
                String cid = foo.elementText("id");
                sambleNode.setCid(cid);
                String name = foo.elementText("name");
                sambleNode.setName(name);
                sambleNode.setParallel("1");
                String predecessor = foo.elementText("predecessor");
                if (predecessor != "") {
                    String[] pstr = predecessor.split(",");
                    if (pstr.length > 1) {
                        for (int i = 0; i < pstr.length; i++) {
                            sambleNode.setProcessor(pstr[i]);
                        }
                    } else {
                        sambleNode.setProcessor(predecessor);
                    }
                }
                String successor = foo.elementText("successor");
                if (successor != "") {
                    String[] sstr = successor.split(",");
                    if (sstr.length > 1) {
                        for (int i = 0; i < sstr.length; i++) {
                            sambleNode.setSucessor(sstr[i]);
                        }
                    } else {
                        sambleNode.setSucessor(successor);
                    }
                }
                nodeList.add(sambleNode);
            }
            traverse(request, json, nodeList.get(0).getCid());
            boolean finishFlag=true;
            while (finishFlag){
                Thread.sleep(10000);
                int count = 0;//check finish or no
                for (int n = 0; n < nodeList.size(); n++) {
                    count = count + Integer.parseInt(checkflag(nodeList.get(n).getCid()));
                }
                if (count == nodeList.size()) {
                    for (int i = 0; i < nodeList.size(); i++) {
                        changflag(nodeList.get(i).getCid());
                    }
                    finishFlag = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //JSONObject json=new JSONObject();
        return allResult.toString();
    }

    void traverse(HttpServletRequest request, String json, String cid) {
        nodeModel tempNode = new nodeModel();
        for(int i=0;i<nodeList.size();i++)
        {
            if (cid.equals( nodeList.get(i).getCid())) {
                tempNode = nodeList.get(i);
            }
        }
        try {
         exec(request, json, tempNode.getName(),tempNode.getCid());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (tempNode.getSucessor().size()>0) {
            boolean fend = true;
            while (fend == true) {
                try {
                    Thread.sleep(3000);
                    if (checkflag(tempNode.getCid()).equals("1")) {
                        fend = false;
                        for (int i = 0; i < tempNode.getSucessor().size(); i++) {
                            nodeModel tempNode1 = new nodeModel();
                            for (int k = 0; k < nodeList.size(); k++) {
                                if (tempNode.getSucessor().get(i).equals(nodeList.get(k).getCid())){
                                    tempNode1 = nodeList.get(k);
                                }
                            }
                            boolean end = true;
                            while (end == true) {
                                try {
                                    Thread.sleep(20000);
                                    int count = 0;//check finish or no
                                    for (int n = 0; n < tempNode1.getProcessor().size(); n++) {
                                        count = count + Integer.parseInt(checkflag(tempNode1.getProcessor().get(n)));
                                    }
                                    if (count == tempNode1.getProcessor().size()) {
                                        traverse(request, json, tempNode.getSucessor().get(i));
                                        end = false;
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    String checkflag(String cid) {
        String result;
        result = componentsService.getFlag(cid);
        System.out.println(result);
        return result;
    }

    void changflag(String cid) {

        componentsService.changeFlag(cid);

    }

    void exec(HttpServletRequest request, String json, String cName,String cid) throws IOException {
        JSONArray arr = JSONArray.fromObject(JSON.parse(json));
        String demensionNum = "0", background = "0", pcaInFilePath = "", pcaInFileName = "", pcaOutHdrPath = "", pcaOutResultPath = "",
                randomNum = "", ppiInFilePath = "", ppiInFileName = "", ppiOutResultPath = "", picPiexl = "", sadinFilePath = "",
                sadinFileName = "", sadinLibraryPath = "", sadinLibraryName = "", sadinResultPath = "", sadoutpath = "",
                kmeansinFilePath = "", kmeansinFileName = "", kmeansclassNum = "", kmeansclassCenter = "", kmeansclassResultPic="",
                nfinderinFilePath = "", nfinderinFileName = "", nfinderpixNum = "", nfinderoutPiexlPath = "";

        for (int i = 0; i < arr.size(); i++) {
            JSONObject temp = (JSONObject) arr.get(i);
            int keySize = temp.keySet().size();
            if (keySize > 1) {
                if (temp.getString("name").equals("pca")) {
                    pcaInFilePath = temp.getString("inFilePath");
                    pcaInFileName = temp.getString("inFileName");
                    demensionNum = temp.getString("demensionNum");
                    pcaOutResultPath = temp.getString("outResultPath");
                } else if (temp.getString("name").equals("ppi")) {
                    ppiInFilePath = temp.getString("inFilePath");
                    ppiInFileName = temp.getString("inFileName");
                    randomNum = temp.getString("randomNum");
                    ppiOutResultPath = temp.getString("outPiexlPath");
                    picPiexl = temp.getString("picPiexl");
                } else if (temp.getString("name").equals("sad")) {
                    sadinFilePath = temp.getString("inFilePath");
                    sadinFileName = temp.getString("inFileName");
                    sadinLibraryPath = temp.getString("inLibraryPath");
                    sadinLibraryName = temp.getString("inLibraryName");
                    sadinResultPath = temp.getString("inResultPath");
                    sadoutpath = temp.getString("outPath");
                } else if (temp.getString("name").equals("kmeans")) {
                    kmeansinFilePath = temp.getString("inFilePath");
                    kmeansinFileName = temp.getString("inFileName");
                    kmeansclassNum = temp.getString("classNum");
                    kmeansclassCenter = temp.getString("classCenter");
                    kmeansclassResultPic = temp.getString("classResultPic");
                } else if (temp.getString("name").equals("nfinder")) {
                    nfinderinFilePath = temp.getString("inFilePath");
                    nfinderinFileName = temp.getString("inFileName");
                    nfinderpixNum = temp.getString("pixNum");
                    nfinderoutPiexlPath = temp.getString("outPiexlPath");
                }
            }
        }
            String jobname = "";
            String orginal = "";
            String result = "";
            String mappath = "/home/hadoop/sparkprogram/apache-tomcat-9.0.19/webapps/ROOT/app/img";
            Map<String, String> map = new HashMap<>();
            // projectServerpath for storing
            String projectServerPath = request.getScheme() + "://" + request.getServerName() + ":" +
                    request.getServerPort() + request.getContextPath() + "/rest/app/img/";
            String path = request.getSession().getServletContext().getRealPath("/app/img/");


            if (cName.equals("PPI")) {
                jobname = ppiInFilePath + System.currentTimeMillis(); // job's name
                result = picPiexl + "_Result.jpg";
                String resulttxt = ppiOutResultPath + "_Result.txt";
                CloseableHttpClient client = HttpClients.createDefault();
                //HTTP请求类型创建HttpPost实例
                HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
                //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
                post.setHeader("Content-Type", "application/json;charset=UTF-8");
                //组织数据

                JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
                jobSubmitRequest.setAction("CreateSubmissionRequest");
                jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars190805/Spark_PPI.jar");
                jobSubmitRequest.setClientSparkVersion("2.3.3");

                List<String> params = new ArrayList<>();
                String[] argss = {ppiInFileName, ppiInFilePath, randomNum,
                        ppiOutResultPath, picPiexl, path,cid};

                params.addAll(Arrays.asList(argss)); // add args to the args list for spark
                jobSubmitRequest.setAppArgs(params);

                Map<String, String> environment = new HashMap<>();
                environment.put("SPARK_ENV_LOADED", "1");
                jobSubmitRequest.setEnvironmentVariables(environment);

                jobSubmitRequest.setMainClass("jingle.ppi.Extraction");

                Map<String, String> sparkProperties = new HashMap<>();
                sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190805/Spark_PPI.jar");
                sparkProperties.put("spark.driver.supervise", "false");
                sparkProperties.put("spark.app.name", jobname);
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
                map.put("result", projectServerPath + picPiexl);
                map.put("resulttxt", projectServerPath +ppiOutResultPath);
//                map.put("filename", projectServerPath + ppiInFileName + ".jpg");
                map.put("jobname", jobname);
                map.put("spark", resData);
                allResult.add(map);
//                JSONObject jResult = new JSONObject();
//                jResult.put("code", 200);
//                jResult.put("ppi", map);

                //return jResult.toString();


            } else if (cName.equals("SAD")) {
                jobname = sadinFileName + System.currentTimeMillis(); // job's name
                CloseableHttpClient client = HttpClients.createDefault();
                //HTTP请求类型创建HttpPost实例
                HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
                //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
                post.setHeader("Content-Type", "application/json;charset=UTF-8");
                //组织数据

                JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
                jobSubmitRequest.setAction("CreateSubmissionRequest");
                jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars190805/spack_sad.jar");
                jobSubmitRequest.setClientSparkVersion("2.3.3");

                List<String> params = new ArrayList<>();
                //???????????????????????
                String[] argss = {sadinFileName, sadinFilePath, sadinLibraryPath, sadinLibraryName, sadinResultPath, "0", "191",
                        sadoutpath, path,cid};

                params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
                jobSubmitRequest.setAppArgs(params);

                Map<String, String> environment = new HashMap<>();
                environment.put("SPARK_ENV_LOADED", "1");
                jobSubmitRequest.setEnvironmentVariables(environment);

                jobSubmitRequest.setMainClass("yk.sad.SADMain");

                Map<String, String> sparkProperties = new HashMap<>();
                sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190805/spack_sad.jar");//???
                sparkProperties.put("spark.driver.supervise", "false");
                sparkProperties.put("spark.app.name", jobname);
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

        //        map.put("original", projectServerPath + orginal);
                map.put("result", projectServerPath + sadoutpath);
                map.put("jobname", jobname);
                map.put("spark", resData);

//                JSONObject jResult = new JSONObject();
//                jResult.put("code", 200);
//                jResult.put("data", map);
//                return jResult.toString();
                allResult.add(map);
            } else if (cName.equals("NFindr")) {
                jobname = nfinderinFileName + System.currentTimeMillis(); // job's name
                CloseableHttpClient client = HttpClients.createDefault();
                //HTTP请求类型创建HttpPost实例
                HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
                //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
                post.setHeader("Content-Type", "application/json;charset=UTF-8");
                //组织数据

                JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
                jobSubmitRequest.setAction("CreateSubmissionRequest");
                jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars190805/Unmixing_NFinder.jar");
                jobSubmitRequest.setClientSparkVersion("2.3.3");

                List<String> params = new ArrayList<>();
                //???????????????????????
                String[] argss = {nfinderinFileName, nfinderinFilePath, nfinderpixNum, nfinderoutPiexlPath,cid};

                params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
                jobSubmitRequest.setAppArgs(params);

                Map<String, String> environment = new HashMap<>();
                environment.put("SPARK_ENV_LOADED", "1");
                jobSubmitRequest.setEnvironmentVariables(environment);

                jobSubmitRequest.setMainClass("lyl.unmixing.Unmixing");

                Map<String, String> sparkProperties = new HashMap<>();
                sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190805/Unmixing_NFinder.jar");//???
                sparkProperties.put("spark.driver.supervise", "false");
                sparkProperties.put("spark.app.name", jobname);
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
                map.put("result", projectServerPath + result);
                map.put("jobname", jobname);
                map.put("spark", resData);

//                JSONObject jResult = new JSONObject();
//                jResult.put("code", 200);
//                jResult.put("data", map);
//                return jResult.toString();
            } else if (cName.equals("KMeans")) {
                jobname = kmeansinFileName + System.currentTimeMillis(); // job's name

                CloseableHttpClient client = HttpClients.createDefault();
                //HTTP请求类型创建HttpPost实例
                HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
                //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
                post.setHeader("Content-Type", "application/json;charset=UTF-8");
                //组织数据

                JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
                jobSubmitRequest.setAction("CreateSubmissionRequest");
                jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars190805/kmeans.jar");
                jobSubmitRequest.setClientSparkVersion("2.3.3");

                List<String> params = new ArrayList<>();
                //???????????????????????
                String[] argss = {kmeansinFileName, kmeansinFilePath, kmeansclassNum, kmeansclassCenter, kmeansclassResultPic, path,cid};

                params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
                jobSubmitRequest.setAppArgs(params);

                Map<String, String> environment = new HashMap<>();
                environment.put("SPARK_ENV_LOADED", "1");
                jobSubmitRequest.setEnvironmentVariables(environment);

                jobSubmitRequest.setMainClass("yk.MainLoopSpark");

                Map<String, String> sparkProperties = new HashMap<>();
                sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190805/kmeans.jar");
                sparkProperties.put("spark.driver.supervise", "false");
                sparkProperties.put("spark.app.name", jobname);
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

           //     map.put("original", projectServerPath + orginal);
                map.put("result", projectServerPath + kmeansinFilePath);
                map.put("resultpic", projectServerPath + kmeansclassResultPic);
                map.put("jobname", jobname);
                map.put("spark", resData);
                allResult.add(map);
//
//                JSONObject jResult = new JSONObject();
//                jResult.put("code", 200);
//                jResult.put("data", map);
//                return jResult.toString();
            } else if (cName.equals("PCA")) {
                jobname = pcaInFileName + System.currentTimeMillis(); // job's name
                CloseableHttpClient client = HttpClients.createDefault();
                //HTTP请求类型创建HttpPost实例
                HttpPost post = new HttpPost("http://10.10.10.105:6066/v1/submissions/create");
                //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
                post.setHeader("Content-Type", "application/json;charset=UTF-8");
                //组织数据

                JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
                jobSubmitRequest.setAction("CreateSubmissionRequest");
                jobSubmitRequest.setAppResource("hdfs://10.10.10.64:9000/jars190805/Pcaspack.jar");
                jobSubmitRequest.setClientSparkVersion("2.3.3");

                List<String> params = new ArrayList<>();
                //???????????????????????
                String[] argss = {pcaInFilePath, pcaInFileName, demensionNum, pcaOutResultPath, mappath,cid};

                params.addAll(Arrays.asList(argss)); // add args to the args list for spark
//                System.out.println(pa);
                jobSubmitRequest.setAppArgs(params);

                Map<String, String> environment = new HashMap<>();
                environment.put("SPARK_ENV_LOADED", "1");
                jobSubmitRequest.setEnvironmentVariables(environment);

                jobSubmitRequest.setMainClass("lyl.pca.PCAMain");

                Map<String, String> sparkProperties = new HashMap<>();
                sparkProperties.put("spark.jars", "hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190805/Pcaspack.jar");
                sparkProperties.put("spark.driver.supervise", "false");
                sparkProperties.put("spark.app.name", jobname);
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


                map.put("result", projectServerPath + pcaOutResultPath);
                map.put("jobname", jobname);
                map.put("spark", resData);

//                JSONObject jResult = new JSONObject();
//                jResult.put("code", 200);
//                jResult.put("data", map);
                allResult.add(map);
               // return jResult.toString();
            }

//            JSONObject jResult = new JSONObject();
//            jResult.put("code", 200);
//            jResult.put("data", map);
////            jResult.put("result",re)
//            return jResult.toString();
        }

    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String jobState(){
        JSONObject jResult = new JSONObject();
        List<StateModel> list = componentsService.getAllState();
        jResult.put("data", list );
        jResult.put("code", 200);
        jResult.put("total", list.size());
        return jResult.toString();

    }

    }





