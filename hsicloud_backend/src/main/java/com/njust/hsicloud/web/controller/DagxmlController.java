package com.njust.hsicloud.web.controller;

import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.core.assist.runc.sqloperaters;
import com.njust.hsicloud.web.dao.ExcutiveMapper;
import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.Excutive;
import com.njust.hsicloud.web.model.JobSubmitRequest;
import com.njust.hsicloud.web.service.ComponentsService;
import com.njust.hsicloud.web.service.ExcutiveService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.avro.generic.GenericData;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.njust.hsicloud.core.entity.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

import java.io.File;
import java.io.FileOutputStream;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;


@Controller
@RequestMapping("/dag")
public class DagxmlController {

    @Autowired
    private ExcutiveService excutiveService;
    @RequestMapping(value = "/write", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String  processParamtoxml(@RequestParam("list")  String json) throws IOException, InterruptedException {

        JSONArray jsonArray = JSONArray.fromObject(JSON.parse(json));
        String  daglink="";
        daglink ="/home/hadoop/Desktop/quick4j-master/src/main/java/com/eliteams/quick4j/web/data/result/"+ System.currentTimeMillis() +".xml";// 取当前时间戳作为文件名
        System.out.println(jsonArray);
        int nodesLen=((JSONObject)jsonArray.get(0)).getJSONArray("nodes").size();
        int edgesLen=((JSONObject)jsonArray.get(0)).getJSONArray("edges").size();
        DAGNodeModel [] dagmodel=new DAGNodeModel [nodesLen];
        for(int i=0;i<nodesLen;i++)
        {
            ArrayList<String> plist=new ArrayList<String>();
            ArrayList<String> slist=new ArrayList<String>();
            String name=((JSONObject)((JSONObject)jsonArray.get(0)).getJSONArray("nodes").get(i)).getString("name");
            String id=((JSONObject)((JSONObject)jsonArray.get(0)).getJSONArray("nodes").get(i)).getString("id");
            DAGNodeModel node = creatModel(Integer.parseInt(id), name, plist, slist);
            dagmodel[i]=node;
        }
        for(int i=0;i<edgesLen;i++)
        {
            int predecessor=Integer.parseInt(((JSONObject)((JSONObject)jsonArray.get(0)).getJSONArray("edges").get(i)).getString("src_node_id"));
            int successor=Integer.parseInt(((JSONObject)((JSONObject)jsonArray.get(0)).getJSONArray("edges").get(i)).getString("dst_node_id"));
            for(int j=0;j<nodesLen;j++)
            {
                if(predecessor==dagmodel[j].getId()){
                    for(int k=0;k<nodesLen;k++){
                        if(successor==dagmodel[k].getId()){
                            dagmodel[j].setSuccessor(String.valueOf(dagmodel[k].getId()));
                            dagmodel[k].setPredecessor(String.valueOf(dagmodel[j].getId()));
                        }

                    }
                }
            }
        }
        JSONObject excuterid = new JSONObject();

      //  String path="/home/hadoop/Desktop/quick4j-master 2/src/main/java/com/eliteams/quick4j/web/data/result/result.xml";
        writeToXml(dagmodel,daglink);

        Excutive exModel=new Excutive();
        exModel.setDagLink(daglink);
        exModel.setResult("result,");
        System.out.println(excutiveService);
        excutiveService.insertSelective(exModel);
        int id=exModel.getId();
        excuterid.put("data", id );
        excuterid.put("code", 200 );
        return excuterid.toString();
    }


    public static DAGNodeModel creatModel(int id,String name,ArrayList<String> predecessor,ArrayList<String> successor) {
        String url="/home/hadoop/Desktop/quick4j-master/src/main/java/com/eliteams/quick4j/core/assist/xmlFile/"+name+".xml";
        boolean flag=true;
        DAGNodeModel node=new DAGNodeModel(id,name,url,flag,predecessor,successor);
        return node;
    }

    public static void writeToXml(DAGNodeModel[] dagmodel,String path) {
        try { // 1、创建document对象
            DocumentHelper.createDocument();
            Document document = DocumentHelper.createDocument();
            // 2、创建根节点rss
             Element DAG = document.addElement("DAG");
            // 3、向rss节点添加version属性
            // DAG.addAttribute("version", "2.0");
            // 4、生成子节点及子节点内容
            Element dagname = DAG.addElement("dagname");
            dagname.setText("DAG1");
            Element VMnum = DAG.addElement("VMnum");
            VMnum.setText("4");
            Element ImageID = DAG.addElement("ImageID");
            ImageID.setText("0");
            Element object = DAG.addElement("object");
            object.setText("maxprofit");
            Element nodes = DAG.addElement("nodes");
            for(int i=0;i<dagmodel.length;i++)
            {
                Element node = nodes.addElement("node");
                Element id = node.addElement("id");
                id.setText(String.valueOf(dagmodel[i].getId()));
                Element name = node.addElement("name");
                name.setText(dagmodel[i].getName());
                Element url = node.addElement("url");
                url.setText(dagmodel[i].getUrl());
                Element flag = node.addElement("flag");
                flag.setText("true");
                if(dagmodel[i].getPredecessor()!=null) {
                    Element predecessor = node.addElement("predecessor");
                    predecessor.setText(String.join(",",  dagmodel[i].getPredecessor().toArray(new String[dagmodel[i].getPredecessor().size()])));
                }
                if(dagmodel[i].getSuccessor()!=null) {
                    Element successor = node.addElement("successor");
                    successor.setText(String.join(",",  dagmodel[i].getSuccessor().toArray(new String[dagmodel[i].getSuccessor().size()])));
                }
            }
            // 5、设置生成xml的格式
            OutputFormat format = OutputFormat.createPrettyPrint();
            // 设置编码格式
            format.setEncoding("UTF-8");
            // 6、生成xml文件
            File file = new File(path);
            XMLWriter writer = new XMLWriter(new FileOutputStream(file), format);
            // 设置是否转义，默认使用转义字符
            writer.setEscapeText(false);
            writer.write(document);
            writer.close();
            System.out.println("dag.xml成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
           System.out.println("生成dag.xml失败");
        }
    }

//        for(int i=0;i<applicationJson.size();i++)
//        {
//
//        }
//
//        String componentName = applicationJson.getString("name");
//        String jobname = "";
//        String orginal = "";
//        String result = "";
//        Map<String, String> map = new HashMap<>();
//        // projectServerpath for storing
//        String projectServerPath = request.getScheme() + "://" + request.getServerName() + ":" +
//                request.getServerPort() + request.getContextPath() + "/app/hdr/";
//        String path = request.getSession().getServletContext().getRealPath("/app/hdr/");
//
////            System.out.println(componentName);
//        if (componentName.equals("大米检测")) {
////                JSONObject input = JSONObject.fromObject(applicationJson.getString("input"));
//            JSONArray components = applicationJson.getJSONArray("components");
//            JSONObject rice = JSONObject.fromObject(components.get(0));
//            JSONArray args = JSONArray.fromObject(rice.getString("input"));
//            String filename = "", good = "2500", normal = "1500", bad = "4000", background = "0";
//            for (int i = 0; i < args.size(); i++) {
//                JSONObject temp = JSONObject.fromObject(args.get(i));
////                    JSONArray temp1 = JSONArray.fromObject(temp.getString("input"));
//                switch (temp.getString("en")) {
//                    case "imagename":
//                        filename = temp.getString("value");
//                        break;
//                    case "good":
//                        good = temp.getString("value");
//                        break;
//                    case "normal":
//                        normal = temp.getString("value");
//                        break;
//                    case "bad":
//                        bad = temp.getString("value");
//                        break;
//                    case "background":
//                        background = temp.getString("value");
//                        break;
//                }
//            }
//            System.out.println(path);
//
//            jobname = filename + System.currentTimeMillis(); // job's name
//            orginal = filename + "_Original.jpg";
//            result = filename + "_Result.jpg";
        }



