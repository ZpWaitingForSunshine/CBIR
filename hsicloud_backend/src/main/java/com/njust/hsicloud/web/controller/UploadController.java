package com.njust.hsicloud.web.controller;

import com.njust.hsicloud.core.util.HDFSUtil;
import com.njust.hsicloud.core.util.upload.HDFSOperation;
import com.njust.hsicloud.web.model.Envi;
import com.njust.hsicloud.web.service.ImageService;
import org.apache.spark.deploy.SparkSubmit;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "/upload")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UploadController {

    @Resource
    ImageService imageService;


    /**
     * upload hdr file
     * @param request
     * @param file
     */
    @RequestMapping("/hdrupload")
    public @ResponseBody
    void hdrUpload(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
//        System.out.println(file);
        String filename = file.getOriginalFilename(); // get original
        String projectServerPath = request.getScheme() + "://"+request.getServerName()+":" +
                request.getServerPort() + request.getContextPath() + "/app/hdr/";
        String path = request.getSession().getServletContext().getRealPath("/app/hdr/");
        System.out.println(projectServerPath);
        System.out.println(path);

        HDFSOperation hdfsOperation = new HDFSOperation();
        String dfspath = hdfsOperation.getDfsPath();//上传路径
        File newFile = new File(path + filename);
        if(!newFile.exists())
            newFile.createNewFile();
//                // 将内存中的数据写入磁盘
        file.transferTo(newFile);
        hdfsOperation.upload(file.getInputStream(), dfspath + filename);
        Envi image = HDFSUtil.getImageMeta(file.getInputStream(), filename);
        image.setHdfs(dfspath+filename.substring(0,filename.length()-4) + ".img");

        String Thumbnailname = projectServerPath + filename.substring(0,filename.length()-4) + ".jpg";
        image.setThumbnailurl(Thumbnailname);
        imageService.insertEnvi(image);


    }

    @RequestMapping("/imgupload")
    public @ResponseBody
    void imgUpload(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
//        System.out.println(file);
        String filename = file.getOriginalFilename(); // get origrinal
        String dfilename = filename.substring(0, filename.indexOf(".img")); // get the file name without type

        Envi envi = imageService.getByFilename(dfilename); // get the same name image hdr
        HDFSOperation hdfsOperation = new HDFSOperation();
        String dfspath = hdfsOperation.getDfsPath();//上传路径
        if(envi != null){
            HDFSUtil.GenerateThumbnail(file.getInputStream(),envi);
            hdfsOperation.upload(file.getInputStream(), dfspath + filename , envi);
        }
        else
            System.out.println("no image");
    }



    @RequestMapping("/editItemsSubmitJson")
    public @ResponseBody
    void editItemsSubmitJSON(HttpServletRequest request, @RequestParam("file") MultipartFile[] files)
            throws Exception {
        HDFSOperation hdfsOperation;
        try {
            hdfsOperation = new HDFSOperation();
            String dfspath = hdfsOperation.getDfsPath();//上传路径
            String rootpath = "/home/hadoop/test1/hdr/";

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
    private String remoteServer = "http://10.10.10.79:8088";
    private String remoteFileUri = "http://10.10.10.79:8088/rest/uploadService/save";
    /**
     * 上传文件
     */
    @RequestMapping("/uploadfile")
//   @RequiresPermissions("sys:oss:all")
    public  @ResponseBody
    String  upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("上传文件不能为空");
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String prefix = UUID.randomUUID().toString().replaceAll("-", "");
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        File tempFile = null;

        String url = "";
        //上传到指定的服务器项目
        try {
            tempFile = File.createTempFile(prefix, suffix);
            if (!file.isEmpty()) {
                String serverUrl = remoteFileUri ;
                RestTemplate restTemplate = new RestTemplate();
                file.transferTo(tempFile);
                //构建 HttpEntity
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
                body.add("file", new FileSystemResource(tempFile));
                HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
                ResponseEntity re = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
                url=re.getBody()+"";
                            if (re.getStatusCode().value() == 200) {
                    url = re.getBody() + "";
                } else {
                    url = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        return url;
    }

}
