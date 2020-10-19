package com.njust.hsicloud.web.controller;

import ch.ethz.ssh2.Connection;
import com.njust.hsicloud.core.assist.algrithm.testMain;
import com.njust.hsicloud.core.assist.gantchart.displayGanttChart;
import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.DagConponent;
import com.njust.hsicloud.web.model.StateModel;
import com.njust.hsicloud.web.model.nodeModel;
import com.njust.hsicloud.web.service.ComponentsService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.njust.hsicloud.core.assist.runc.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/savedga")
public class Savedag {
    @Autowired
    private ComponentsService componentsService;

    @RequestMapping(value = "/getInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getInfo(HttpServletRequest request, @RequestParam("cname") String json){
       String  ccname=String.valueOf(json);
       // String ccname="woshiname";
        JSONObject jResult = new JSONObject();
        List<DagConponent> list = componentsService.getAllComponentsInfo(ccname);
        jResult.put("data", list );
        jResult.put("code", 200);
        jResult.put("total", list.size());
        return jResult.toString();
    }
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    void saveInfo(HttpServletRequest request, @RequestParam("info") String json){
       // JSONObject daginfo = JSONObject.fromObject(json);
        JSONArray arrayinfo= JSONArray.fromObject(json);
        String ccname=String.valueOf(arrayinfo.get(arrayinfo.size()-1));
        String dag_json="";
        for(int i=0;i<arrayinfo.size()-1;i++){
            dag_json=dag_json+String.valueOf(arrayinfo.get(i));
        }
        componentsService.saveDagInfo(ccname,dag_json);
    }
    @RequestMapping(value = "/dodispatch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String dispatch (HttpServletRequest request, @RequestParam("pid") String pid, @RequestParam("eid") String eid){
        String kpid=pid;
        String keid=eid;
        String dispatchurl="";
        String resultPath="";
        switch (kpid){
            case "选项1":
                try {
                    resultPath="/home/hadoop/Desktop/quick4j-master/src/main/java/com/eliteams/quick4j/web/data/xml/"+System.currentTimeMillis()+".xml";
                    Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","/home/hadoop/Desktop/quick4j-master/lib/DAGSchedualCloudsim.jar","/home/hadoop/data/result.xml","4",resultPath,"1"});
                    ps.waitFor();
                    java.io.InputStream is=ps.getInputStream();
                    byte b[]=new byte[is.available()];
                    is.read(b,0,b.length);
                    System.out.println(new String(b));
                }catch (Exception e){
                    e.printStackTrace();
                }
//               transportxml("/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/core/assist/data/result.xml",path);
                break;
            case "选项3":
                try {
                    resultPath="/home/hadoop/Desktop/quick4j-master/src/main/java/com/eliteams/quick4j/web/data/xml/"+System.currentTimeMillis()+".xml";
                    Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","/home/hadoop/Desktop/quick4j-master/lib/CloudsimCuckoo.jar","/home/hadoop/data/result.xml",resultPath,"4","1"});
                    ps.waitFor();
                    java.io.InputStream is=ps.getInputStream();
                    byte b[]=new byte[is.available()];
                    is.read(b,0,b.length);
                    System.out.println(new String(b));
                }catch (Exception e){
                    e.printStackTrace();
                }        break;
            case "选项2":
                try {
                    resultPath="/home/hadoop/Desktop/quick4j-master/src/main/java/com/eliteams/quick4j/web/data/xml/"+System.currentTimeMillis()+".xml";
                    Process ps=Runtime.getRuntime().exec(new String[]{"java","-jar","/home/hadoop/Desktop/quick4j-master/lib/pso.jar","/home/hadoop/data/result.xml",resultPath,"4","1"});
                    ps.waitFor();
                    java.io.InputStream is=ps.getInputStream();
                    byte b[]=new byte[is.available()];
                    is.read(b,0,b.length);
                    System.out.println(new String(b));
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
        try {
            File file = new File(resultPath);
            while (true) {
                if (file.exists()) {
                    dispatchurl = saveResult(resultPath);
                    updatexcutive("dispatch_local_link", resultPath, Integer.parseInt(eid));
                    updatexcutive("dispatch_result", dispatchurl, Integer.parseInt(eid));
                    break;
                } else {
                    System.out.println("wait dispatch result");
                }
            }
        }
            catch(Exception e){
                e.printStackTrace();
            }
        JSONObject jResult = new JSONObject();
        jResult.put("data", dispatchurl);
        jResult.put("code", 200);
        return jResult.toString();
    }

    @RequestMapping(value = "/getName", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getName(){
        JSONObject jResult = new JSONObject();
        List<DagConponent> list = componentsService.getAllComponentsName();
        jResult.put("data", list );
        jResult.put("code", 200);
        return jResult.toString();
    }

    @RequestMapping(value = "/geturl", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getdispatchurl( @RequestParam("eid") String eid){
        JSONObject jResult = new JSONObject();
        String url=getValue("dispatch_result",Integer.parseInt(eid));
        jResult.put("data", url );
        jResult.put("code", 200);
        return jResult.toString();
    }
    //这里为了方便 parallel的值为执行时间
    @RequestMapping(value = "/getGantt", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getGant( @RequestParam("eid") String eid){
        ArrayList<nodeModel> nodeList = new ArrayList<nodeModel>();
        int id =Integer.parseInt(eid);
        JSONObject dispatchResult = new JSONObject();
        String dispatch_local_link =getValue("dispatch_local_link",id);
        try{
            File f1 = new File(dispatch_local_link);
            SAXReader reader1 = new SAXReader();
            Document doc1 = reader1.read(f1);
            Element root1 = doc1.getRootElement();
            Element timetable = root1.element("DagInfo");
            for (Iterator TaskInfo = timetable.elementIterator("TaskInfo"); TaskInfo.hasNext(); ) {
                Element foo = (Element) TaskInfo.next();
                String CloudletID = foo.elementText("CloudletID");
                String startTime = foo.elementText("StartTime");
                String endTime = foo.elementText("FinishTime");
                String duration=String.valueOf(new Double((Double.parseDouble(endTime)-Double.parseDouble(startTime))/1000).intValue());
                String startTime1 = String.valueOf(new Double(Double.parseDouble(startTime)/1000).intValue());
                nodeModel nm=new nodeModel();
                nm.setName(CloudletID);
                nm.setStartTime(startTime1);
                nm.setParallel(duration);
                int count=0;
                for (int i = 0; i < nodeList.size(); i++) {
                    if (nodeList.get(i).getName().equals(CloudletID)) {
                    count=count+1;
                    }
                }
                if(count==0){
                    nodeList.add(nm);
                }

            }
        }

     catch (Exception e) {
        e.printStackTrace();
    }
        dispatchResult.put("data", nodeList);
        dispatchResult.put("code", 200);
        return dispatchResult.toString();
    }
    public String getValue(String coloum,int id) {
        String result;
        result = componentsService.getValue(coloum,id);
        System.out.println(result);
        return result;
    }
    public void updatexcutive(String coloum,String value,int id){
        componentsService.updatexcutive(coloum,value,id);
    }
    public static String saveResult(String filepath) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String url="";
        //CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        try {
            HttpPost httppost = new HttpPost("http://10.10.10.79:8088/rest/uploadService/save");

            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
            httppost.setConfig(requestConfig);

            FileBody bin = new FileBody(new File(filepath));
            StringBody comment = new StringBody("This is comment", ContentType.TEXT_PLAIN);

            HttpEntity reqEntity = MultipartEntityBuilder.create().addPart("file", bin).addPart("comment", comment).build();

            httppost.setEntity(reqEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    url=responseEntityStr;
                    System.out.println(responseEntityStr);
                    System.out.println("Response content length: " + resEntity.getContentLength());
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return url;
    }
//    public static void transportxml(String oldpath,String newpath){
//        File af = new File(oldpath);
//        File bf = new File(newpath);
//        FileInputStream is = null;
//        FileOutputStream os = null;
//        if(!bf.exists()){
//            try {
//                bf.createNewFile();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        try {
//            is = new FileInputStream(af);
//            os = new FileOutputStream(bf);
//            byte b[] = new byte[1024];
//            int len;
//            try {
//                len = is.read(b);
//                while (len != -1) {
//                    os.write(b, 0, len);
//                    len = is.read(b);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                if(is != null) is.close();
//                if(os != null) os.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("Transport OK!");
//    }

}
