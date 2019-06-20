package com.njust.rsCloud.web.controller;

import com.njust.rsCloud.core.util.HDFSOperation;
import com.njust.rsCloud.core.util.HDFSUtil;
import com.njust.rsCloud.core.util.InputStreamReaderRunnable;
import com.njust.rsCloud.web.model.Envi;
import com.njust.rsCloud.web.service.ImageService;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.SparkSubmit;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
@RequestMapping(value = "/upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UploadController {

    @Resource
    ImageService imageService;

    @RequestMapping("/editItemsSubmitJson")
    public @ResponseBody
    void editItemsSubmitJSON(HttpServletRequest request, @RequestParam("file") MultipartFile[] files)
            throws Exception {
        HDFSOperation hdfsOperation;
        try {
            hdfsOperation = new HDFSOperation();
            String dfspath = hdfsOperation.getDfsPath();//上传路径
            String rootpath = "/home/hadoop/test1/hdr/";
            System.out.println(files.length);

            for(int i = 0; i < files.length; i ++){
                MultipartFile multipartFile = files[i];
                String filename = multipartFile.getOriginalFilename();
                File newFile = new File(rootpath + filename);

                //如果是头文件
                if(filename.endsWith(".hdr")) {

                    if(!newFile.exists())
                        newFile.createNewFile();
//                // 将内存中的数据写入磁盘
                    multipartFile.transferTo(newFile);
                    // get envi data
                    Envi image = HDFSUtil.getImageMeta(multipartFile.getInputStream(), filename);
                    // insert to mysql
                    image.setHdfs(dfspath+filename.substring(0,filename.length()-4)); // dfs
                    String Thumbnailname = filename.substring(0,filename.length()-4) + ".jpg";
                    image.setThumbnailurl("/home/hadoop/test1/hdr/" + Thumbnailname); // url
                    imageService.insertEnvi(image);
//                    HDFSUtil.GenerateThumbnail(multipartFile.getInputStream(), image);//生成缩略图
                    System.out.println(image.toString());
                    boolean flag = false;

                }else if(filename.endsWith(".img")){
                    String dfilename = filename.substring(0, filename.indexOf(".img")); // get the file name without type
                    Envi envi = imageService.getByFilename(dfilename);
                    if(envi != null)
                        hdfsOperation.upload(multipartFile.getInputStream(), dfspath + filename , envi);

                    else
                        System.out.println("no image");

//                    String[] arg0=new String[]{
//                        "--master","spark://10.10.10.96:7077",
//                            "--name","rice",
//                    }
//                    String[] list = new String[1];
//                    list[0] = "/home/hadoop/ricedetection.jar";
//                    SparkConf conf = new SparkConf().setAppName("rice").setJars(list);
//                    conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer");
//                    conf.set("spark.kryo.registrator","xurtx.hsi.spark.KyroRegistratorImpl");
//                    conf.setMaster("local");



                    String[] arg0=new String[]{
                            "--master","spark://10.10.10.96:7077",
                            "--deploy-mode","client",
                            "--name","rice",
                            "--class","DetectionMain",
                            "--executor-memory","8G",
                            "--total-executor-cores","4",
                            "--executor-cores","2",
                            "spark /home/hadoop/ricedetection.jar",


                            "local[*]",
                            "rice",
                            "hdfs://10.10.10.96:9000/CBIRDemo/rice/",
                            filename,
                            "4000","2000","1600","100","/home/hadoop/1.jpg","/home/hadoop/2.jpg"

                    };
                    System.out.println("hhhh2h22");
                    SparkSubmit.main(arg0);



//                "--deploy-mode","client",  
//                "--name","test java submit job to spark",  
//                "--class","Scala_Test",  
//                "--executor-memory","1G",  
////              "spark_filter.jar",  
//                tmp+"lib/spark_filter.jar",//  
//                "hdfs://node101:8020/user/root/log.txt",  
//                "hdfs://node101:8020/user/root/badLines_spark_"+filename  
//        }
//                    HashMap env = new HashMap();
//                    //这两个属性必须设置
//                    env.put("HADOOP_CONF_DIR","/home/hadoop/sparkprogram/hadoop-2.7.7/etc/hadoop");
//                    env.put("JAVA_HOME","/home/hadoop/sparkprogram/jdk1.8.0_201");
//                    //env.put("YARN_CONF_DIR","");
//                    SparkLauncher handle = new SparkLauncher(env)
//                            .setSparkHome("/home/hadoop/sparkprogram/spark-2.3.3-bin-hadoop2.7")
//                            .setAppResource("/home/hadoop/Desktop/quick4j-master 2/src/main/webapp/app/jar/ricedetection.jar")
////                        .setMainClass("com.learn.spark.SimpleApp")
//                            .setMaster("local[*]")
////                        .setDeployMode("cluster")
////                        .setConf("spark.app.id", "11222")
////                        .setConf("spark.driver.memory", "2g")
////                        .setConf("spark.akka.frameSize", "200")
////                        .setConf("spark.executor.memory", "1g")
////                        .setConf("spark.executor.instances", "32")
////                        .setConf("spark.executor.cores", "3")
////                        .setConf("spark.default.parallelism", "10")
////                        .setConf("spark.driver.allowMultipleContexts","true")
//                            .addAppArgs("hdfs://10.10.10.96:9000/CBIRDemo/rice/ "+filename+" 4000 2000 1600 100 /home/hadoop/test1/hdr/"+filename+"_1.jpg  /home/hadoop/test1/hdr/"+ filename + "_2.jpg")
//                            .setVerbose(true);


//                    Process process =handle.launch();
//                    InputStreamReaderRunnable inputStreamReaderRunnable = new InputStreamReaderRunnable(process.getInputStream(), "input");
//                    Thread inputThread = new Thread((Runnable) inputStreamReaderRunnable, "LogStreamReader input");
//                    inputThread.start();
//
//                    InputStreamReaderRunnable errorStreamReaderRunnable = new InputStreamReaderRunnable(process.getErrorStream(), "error");
//                    Thread errorThread = new Thread((Runnable) errorStreamReaderRunnable, "LogStreamReader error");
//                    errorThread.start();
//
//                    System.out.println("Waiting for finish...");
//                    int exitCode = process.waitFor();
//                    System.out.println("Finished! Exit code:" + exitCode);
                }
            }

            } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
