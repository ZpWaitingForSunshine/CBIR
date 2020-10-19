package com.njust.hsicloud.web.controller;


import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.core.util.runmain;
import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.JobSubmitRequest;
import com.njust.hsicloud.web.model.StateModel;
import com.njust.hsicloud.web.model.nodeModel;
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
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.njust.hsicloud.core.entity.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.io.IOException;
import java.util.*;
import com.njust.hsicloud.core.assist.algrithm.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import com.njust.hsicloud.core.assist.runc.*;
import ch.ethz.ssh2.Connection;
import scala.Int;

@Controller
@RequestMapping("/run")
public class RunControl {
    private Connection conn;
    private String ipAddr;
    private String charset = Charset.defaultCharset().toString();
    private String userName;
    private String password;

    @Autowired
    private ComponentsService componentsService;

    @GetMapping
    @ResponseBody
    public List<Components> getComponentsByParam(Map<String, Object> param) {
        return componentsService.getAllComponents();
    }


    @RequestMapping(value = "/startvm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String processAlgrithm(HttpServletRequest request,@RequestParam("eid") String eid) throws Exception {
        int id=Integer.parseInt(eid);
        String path =getValue("dispatch_local_link",id);
        String vn="0";
        try {
            File f1 = new File(path);
            SAXReader reader1 = new SAXReader();
            Document doc1 = reader1.read(f1);
            Element root1 = doc1.getRootElement();
            Element timetable = root1.element("DagInfo");
            Element vm = timetable.element("VMnum");
            vn=vm.getText();
        }catch (Exception e) {
            e.printStackTrace();
        }
 //       int n=Integer.parseInt(vn);
//        String[] Sname = {"Slave1","Slave2","Slave3","Slave4"};
//        for (int i = 0; i <n ; i++) {
//            RemoteShellTool t=new RemoteShellTool();
//            Connection conn= RemoteShellTool.login("10.37.129.10","root","admin");
//            String cmd="source /root/keystonerc_435;openstack server create --image Spark --security-group 435 --security-group cluster --flavor computer "+Sname[i];
//            String res=t.execute(conn,cmd);
//
//        }
        //create vm and ...
        runmain tool = new runmain("10.37.129.10", "root",
                "admin", "utf-8");//this user and pwd are not these used login ui
        runmain tool2=new runmain("10.10.10.101", "root",
                "admin", "utf-8");
        String[] Sname = {"Slave1","Slave2","Slave3","Slave4"};

        int n = Integer.parseInt(vn);
        for (int i = 0; i <n ; i++) {
            System.out.println(i);
            String result1 = tool.exec("source /root/keystonerc_435;openstack server create --image Spark --security-group 435 --security-group cluster --flavor computer --nic net-id=b9857f38-abd7-46b7-ab31-4f66c0c60db2 "+Sname[i]);
            System.out.println(result1);

        }
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException ie){}

        String res2=tool2.exec("sh main.sh");

        JSONObject jResult = new JSONObject();
        jResult.put("data", vn );
        jResult.put("code", 200);

        return jResult.toString();
    }

    public void delVm(int n){
        runmain tool = new runmain("10.37.129.10", "root",
                "admin", "utf-8");
        runmain tool2=new runmain("10.10.10.101", "root",
                "admin", "utf-8");
        String result1= tool2.exec("sh stop.sh");
        System.out.println(result1);
        String[] vms = {"Slave1","Slave2","Slave3","Slave4"};
        try {
            TimeUnit.SECONDS.sleep(60);
        } catch (InterruptedException ie){}
        for(int i=0;i<n;i++){
            String res=tool.exec("source /root/keystonerc_435;openstack server delete"+vms[i]);
            System.out.println(res);
        }

    }
//    @RequestMapping(value = "/deltvm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
//    public @ResponseBody
//    String delvm(HttpServletRequest request,@RequestParam("vmnames") String vmnames) throws Exception {
//        int id=Integer.parseInt(eid);
//        String path =getValue("dispatch_local_link",id);
//        String vn="0";
//        try {
//            File f1 = new File(path);
//            SAXReader reader1 = new SAXReader();
//            Document doc1 = reader1.read(f1);
//            Element root1 = doc1.getRootElement();
//            Element timetable = root1.element("DagInfo");
//            Element vm = timetable.element("VMnum");
//            vn=vm.getText();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        //       int n=Integer.parseInt(vn);
////        String[] Sname = {"Slave1","Slave2","Slave3","Slave4"};
////        for (int i = 0; i <n ; i++) {
////            RemoteShellTool t=new RemoteShellTool();
////            Connection conn= RemoteShellTool.login("10.37.129.10","root","admin");
////            String cmd="source /root/keystonerc_435;openstack server create --image Spark --security-group 435 --security-group cluster --flavor computer "+Sname[i];
////            String res=t.execute(conn,cmd);
////
////        }
//        //create vm and ...
//        runmain tool = new runmain("10.37.129.10", "root",
//                "admin", "utf-8");//this user and pwd are not these used login ui
//        runmain tool2=new runmain("10.10.10.101", "root",
//                "admin", "utf-8");
//        String[] Sname = {"Slave1","Slave2","Slave3","Slave4"};
//        int n = 4;
//        for (int i = 0; i <n ; i++) {
//            System.out.println(i);
//            String result1 = tool.exec("source /root/keystonerc_435;openstack server create --image Spark --security-group 435 --security-group cluster --flavor computer --nic net-id=b9857f38-abd7-46b7-ab31-4f66c0c60db2 "+Sname[i]);
//            System.out.println(result1);
//
//        }
//        try {
//            TimeUnit.SECONDS.sleep(60);
//        } catch (InterruptedException ie){}
//
//        String res2=tool2.exec("sh main.sh");
//
//        JSONObject jResult = new JSONObject();
//        jResult.put("data", vn );
//        jResult.put("code", 200);
//
//        return jResult.toString();
//    }
//        RemoteShellTool tool = new RemoteShellTool("10.37.129.10", "root",
//                "admin", "utf-8");
//        String result1 = tool.exec("source /root/keystonerc_435;openstack server create --image Spark --security-group 435 --flavor computer stest");

//        String result2 = tool.exec("/bin/bash /root/openSSH.sh");
//        String result3 = tool.exec("/bin/bash /root/format.sh");

 //       System.out.print(result1);

    //     ts.main(new String[]{});
    //  scheduling.main("/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/data/result/result.xml", "46137344", "/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/service/vm/");
    //   testMain.excutor(new String[] {"/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/data/result/result.xml", "46137344", "/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/service/vm/"} );

        /*        Process proc;
        try {
            proc = Runtime.getRuntime().exec("java -jar /home/hadoop/Desktop/quick4j-master 2/lib/startvm/scheduling.jar /home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/data/result/result.xml 44000 /home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/service/vm " +
                    "Class-Path:/home/hadoop/Desktop/quick4j-master 2/lib/startvm/cloudsim-3.0.2.jar:/home/hadoop/Desktop/quick4j-master 2/lib/startvm/dom4j-2.1.1.jar" );
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        }catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
//        Process pr;
//        try {
//            pr = Runtime.getRuntime().exec("java -jar /home/hadoop/Desktop/quick4j-master 2/lib/startvm/Algrithm0617.jar ");
//            BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//            String line = null;
//            while ((line = in.readLine()) != null) {
//                System.out.println(line);
//            }
//            in.close();
//            pr.waitFor();
//        }catch(IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    @RequestMapping(value = "/driverparams", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String analysisXML(HttpServletRequest request, @RequestParam("list") String json,@RequestParam("eid") String eid) {
        //            创建待执行算法列表 添加基本信息
        int vmNumber=0;//启动虚拟机的数量
        ArrayList<nodeModel> nodeList = new ArrayList<nodeModel>();
        int id=Integer.parseInt(eid);
        try {
            String daglink=getValue("dag_link",id);
            File f = new File(daglink);
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();
            Element nodes = root.element("nodes");
            for (Iterator node = nodes.elementIterator("node"); node.hasNext(); ) {
                nodeModel sambleNode = new nodeModel();
                Element foo = (Element) node.next();
                String name = foo.elementText("name");
                sambleNode.setName(name);
                String cid = foo.elementText("id");
                sambleNode.setCid(cid);
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
//            添加调度结果
           String dispatch_local_link =getValue("dispatch_local_link",id);
       //     String dispatch_local_link= "/home/hadoop/Desktop/quick4j-master/src/main/java/com/eliteams/quick4j/core/assist/data/result.xml";
            File f1 = new File(dispatch_local_link);
            SAXReader reader1 = new SAXReader();
            Document doc1 = reader1.read(f1);
            Element root1 = doc1.getRootElement();
            Element timetable = root1.element("DagInfo");
            Element vm = timetable.element("VMnum");
            vmNumber=Integer.parseInt(vm.getText());//启动虚拟机的数量
            for (Iterator TaskInfo = timetable.elementIterator("TaskInfo"); TaskInfo.hasNext(); ) {
                Element foo = (Element) TaskInfo.next();
                String CloudletID = foo.elementText("CloudletID");
                String startTime = foo.elementText("StartTime");
                String parallel = foo.elementText("par");
                for (int i = 0; i < nodeList.size(); i++) {
                    if (nodeList.get(i).getName().equals(CloudletID)) {
                        nodeList.get(i).setParallel(parallel);
                        nodeList.get(i).setStartTime(startTime);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            long starTime = System.currentTimeMillis();
            int check[]=new int[nodeList.size()];
            int len=nodeList.size();
            while (true){
                for (int n =0; n < len; n++) {
                    //计算前驱结点是否完成
                    int count=0;

                    for(int i=0;i<nodeList.get(n).getProcessor().size();i++){
//                        根据当前节点的第i个前驱节点的cid获取其节点的全部信息，并复制给pronode
                        nodeModel pronode=new nodeModel();
                        for(int j=0;j<len;j++){
                           if(nodeList.get(n).getProcessor().get(i).equals(nodeList.get(j).getCid())){
                               pronode=nodeList.get(j);
                           }
                        }
                        if(pronode.getSubmissionid()!=null){
                            if (jobState(pronode.getSubmissionid()).equals("FINISHED")){
                                count=count+1;
                            }
                            else {
                                Thread.sleep(2000);
                            }
                        }
                    }
                    //判断当前节点是否达到运行条件
                    long endTime = System.currentTimeMillis();
//                    头节点运行
                    if(nodeList.get(n).getProcessor().size()==0&&check[n]==0){
                        String sid=exec(request, json,nodeList.get(n).getName(), nodeList.get(n).getCid(), nodeList.get(n).getParallel(),eid);
                        nodeList.get(n).setSubmissionid(sid);
                        check[n]=1;
                        String newsumSubmissionId=nodeList.get(n).getCid()+":"+nodeList.get(n).getSubmissionid()+",";
                        updatexcutive("submission_id",newsumSubmissionId,id);
                    }else {
//                        其他节点运行
                        if(check[n]==0&&endTime - starTime >= Float.valueOf(nodeList.get(n).getStartTime())&&count==nodeList.get(n).getProcessor().size()){
                            String sid=exec(request, json,nodeList.get(n).getName(), nodeList.get(n).getCid(), nodeList.get(n).getParallel(),eid);
                            nodeList.get(n).setSubmissionid(sid);
                            check[n]=1;
                            String sumSubmissionId=getValue("submission_id",id);
                            String newsumSubmissionId=sumSubmissionId+nodeList.get(n).getCid()+":"+nodeList.get(n).getSubmissionid()+",";
                            updatexcutive("submission_id",newsumSubmissionId,id);
                        }
                    }

                }
                int over=0;
                for(int n=0;n<len;n++){
                    over=over+check[n];
                }
                if(over==len){
             //   delVm(vmNumber);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data", "submit ok!" );
        jsonObject.put("code", 200 );
        return jsonObject.toString();
    }
    public void updatexcutive(String coloum,String value,int id){
        componentsService.updatexcutive(coloum,value,id);
    }
    public String getValue(String coloum,int id) {
        String result;
        result = componentsService.getValue(coloum,id);
        return result;
    }

    String jobState(String submissionId) throws Exception {
        String resData="";
        String result="";
        if (submissionId==null){
            result="0";
        }else{

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
            resData = EntityUtils.toString(response.getEntity(),"utf-8");
//        //最后关闭HttpClient资源.
            response.close();
            client.close();
            JSONObject jsonObj = JSONObject.fromObject(resData);
            result= jsonObj.getString("driverState");
        }
       return result;
    }
    private String submitMission(String appResource,String[]  args,String mainclass,String jarsPath,String appName) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost("http://10.10.10.101:6066/v1/submissions/create");
        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        //组织数据
        JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
        jobSubmitRequest.setAction("CreateSubmissionRequest");
        jobSubmitRequest.setAppResource(appResource);
        jobSubmitRequest.setClientSparkVersion("2.3.3");
        List<String> params = new ArrayList<>();
      //  String[] argss = {"hdfs://10.10.10.64:9000/","c350bip_2","15", "hdfs://10.10.10.79:9000/pp1216","11","jdbc:mysql://10.10.10.76:3306/test","pca","http://10.10.10.79:8088/rest/uploadService/save","100"};
        params.addAll(Arrays.asList(args)); // add args to the args list for spark
        jobSubmitRequest.setAppArgs(params);
        Map<String, String> environment = new java.util.HashMap<>();
        environment.put("SPARK_ENV_LOADED", "1");
        jobSubmitRequest.setEnvironmentVariables(environment);
        jobSubmitRequest.setMainClass(mainclass);
        Map<String, String> sparkProperties = new java.util.HashMap<>();
        sparkProperties.put("spark.driver.supervise", "false");
        sparkProperties.put("spark.jars",jarsPath );
        sparkProperties.put("spark.app.name", appName);
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

        //最后关闭HttpClient资源.
        response.close();
        client.close();
        JSONObject jsonObj = JSONObject.fromObject(resData);
        return jsonObj.getString("submissionId");

    }
    String exec(HttpServletRequest request, String json, String cName, String cid, String parallel,String excuteid) throws IOException {
        JSONArray arr = JSONArray.fromObject(JSON.parse(json));
        String demensionNum = "0", pcaInFilePath = "", pcaInFileName = "",  pcaOutResultPath = "",
                randomNum = "", ppiInFilePath = "", ppiInFileName = "", ppiOutResultPath = "", picPiexl = "", sadinFilePath = "",
                sadinFileName = "", sadinLibraryPath = "", sadinLibraryName = "", sadinResultPath = "", sadoutpath = "",
                kmeansinFilePath = "", kmeansinFileName = "", kmeansclassNum = "", kmeansclassCenter = "", kmeansclassResultPic = "",
                nfinderinFilePath = "", nfinderinFileName = "", nfinderpixNum = "", nfinderoutPiexlPath = "";
        String cmd="";
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
        String mainClass;
        String appResource;
        String jarsPath;
        String appname;
        String sid="";

        if (cName.equals("PPI")) {
            String [] args={ppiInFileName,ppiInFilePath,randomNum,ppiOutResultPath,
                    picPiexl,
                    "hdfs://10.10.10.64:9000/",
                    excuteid,
                    "ppires1.txt",
                    "ppires2.jpg",
                    "jdbc:mysql://10.10.10.76:3306/test",
                    cid,
                    "http://10.10.10.79:8088/rest/uploadService/save"};
            mainClass="jingle.ppi.Extraction";
            appname="ppi";
            appResource="hdfs://10.10.10.64:9000/jars191215/Spark_PPI.jar";
            jarsPath="hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190717/httpmime-4.5.3.jar,hdfs://10.10.10.64:9000/jars191215/Spark_PPI.jar";
            sid=submitMission(appResource,args,mainClass,jarsPath,appname);

        } else if (cName.equals("SAD")) {
            String [] args= { sadinFileName, sadinFilePath ,
                    sadinLibraryPath ,sadinLibraryName ,sadinResultPath, "0" ,"191",
                    sadoutpath, "hdfs://10.10.10.64:9000/" ,excuteid ,"name.txt",
                    "jdbc:mysql://10.10.10.76:3306/test", "http://10.10.10.79:8088/rest/uploadService/save", cid, "hdfs://10.10.10.64:9000/"};
            mainClass="yk.sad.SADMain";
            appname="sad";
            appResource="hdfs://10.10.10.64:9000/jars191215/spack_sad.jar";
            jarsPath="hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190717/httpmime-4.5.3.jar,hdfs://10.10.10.64:9000/jars191215/spack_sad.jar";
            sid=submitMission(appResource,args,mainClass,jarsPath,appname);

        } else if (cName.equals("NFindr")) {

        } else if (cName.equals("KMeans")) {

        } else if (cName.equals("PCA")) {
            String [] args={pcaInFilePath,pcaInFileName,demensionNum,
                    pcaOutResultPath,
                    excuteid,"jdbc:mysql://10.10.10.76:3306/test","pca",
                    "http://10.10.10.79:8088/rest/uploadService/save",cid,"hdfs://10.10.10.64:9000/","/home/hadoop/data/chart.png"};
            mainClass="lyl.pca.PCAMain";
            appname="pca";
            appResource="hdfs://10.10.10.64:9000/jars191215/Pcaspack.jar";
            jarsPath="hdfs://10.10.10.64:9000/jars/Jama-1.0.3.jar,hdfs://10.10.10.64:9000/jars190717/jfreechart-1.0.19.jar,hdfs://10.10.10.64:9000/jars190717/jcommon-1.0.23.jar,hdfs://10.10.10.64:9000/jars190717/mysql-connector-java-8.0.16.jar,hdfs://10.10.10.64:9000/jars190717/httpmime-4.5.3.jar,hdfs://10.10.10.64:9000/jars191215/Pcaspack.jar";
            sid=submitMission(appResource,args,mainClass,jarsPath,appname);
        }
    return sid;
    }
//    String status(String appId)  {
//        RemoteShellTool t=new RemoteShellTool();
//        Connection conn= RemoteShellTool.login("10.10.10.166","root","admin");
//        String cmd="curl http://10.10.10.166:8080/api/v1/applications\n";
//        String res=t.execute(conn,cmd);
//        String appstatus="";
//        String[] split = res.split("<tr>");
//
//        for(int i=0;i<split.length;i++){
//            if(split[i].contains("<a href=\"app?appId="+appId+"\">"+appId+"</a>")){
//                String[] td = split[i].split("<td>");
//                appstatus=td[6];
//            }
//        }
//        return appstatus;
//    }
//    void transfeRresult(HttpServletRequest request)  {
//        String path =request.getSession().getServletContext().getRealPath("/app/img/");
//        RemoteShellTool t=new RemoteShellTool();
//        Connection conn= RemoteShellTool.login("10.10.10.166","root","admin");
//        String cmd="~/sendToHadoop.sh "+"/home/hadoop/data "+path;
//        String res=t.execute(conn,cmd);
//        System.out.println(res);
//    }
    @RequestMapping(value = "/check", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getjobState(HttpServletRequest request, @RequestParam("eid") String eid) throws Exception {
        String sumSubmissionId=getValue("submission_id",Integer.parseInt(eid));
        ArrayList<StateModel> statelist = new ArrayList<StateModel>();
        if(sumSubmissionId!=null){
        String [] array=sumSubmissionId.split(",");
        for(int i=0;i<array.length;i++){
            String [] a=array[i].split(":");
            //        //使用帮助类HttpClients创建CloseableHttpClient对象.
            CloseableHttpClient client = HttpClients.createDefault();
//        //HTTP请求类型创建HttpPost实例
            HttpGet get = new HttpGet("http://10.10.10.101:6066/v1/submissions/status/"+a[1]);
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
            JSONObject jsonObj = JSONObject.fromObject(resData);
            if(jsonObj.getString("driverState").equals("FINISHED")){
                StateModel singleState=new StateModel();
                singleState.setCid(Integer.parseInt(a[0]));
                singleState.setValue("1");
                statelist.add(singleState);
            }
            else {
                StateModel singleState=new StateModel();
                singleState.setCid(Integer.parseInt(a[0]));
                singleState.setValue("0");
                statelist.add(singleState);
            }
        }
        }
        JSONObject js = new JSONObject();
        js.put("data", statelist);
        js.put("code", 200 );
        return js.toString();
    }

    @RequestMapping(value = "/getResulturl", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getResulturl( @RequestParam("eid") String eid){
        JSONObject jResult = new JSONObject();
        String url=getValue("result",Integer.parseInt(eid));
        jResult.put("data", url );
        jResult.put("code", 200);
        return jResult.toString();
    }
}
