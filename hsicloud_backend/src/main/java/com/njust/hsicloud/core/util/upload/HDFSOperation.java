package com.njust.hsicloud.core.util.upload;

import com.njust.hsicloud.web.model.Envi;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

@Component
public class HDFSOperation {
    private Configuration conf;
    private FileSystem fs;

    @Value("${hdfs.path}")
    private String hdfspath;

    @Value("${hdfs.hyperspectralPath}")
    private String hyperspectralPath;


    public String getHdfspath() {
        return hdfspath;
    }

    public String getHyperspectralPath() {
        return hyperspectralPath;
    }

    public HDFSOperation(){
        conf = new Configuration();
        conf.set("dfs.client.use.datanode.hostname","true");
    }



    /**
     *
     * @param dfspath
     * @return
     */
    public boolean mkdir(String dfspath){
        Path path = new Path(dfspath);
        try {
            if(!fs.exists(path)){
                fs.mkdirs(path);
            }
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    public String getDfsPath(){
        return conf.get("fs.defaultFS") + hyperspectralPath;
    }

    /**
     *
     * @param in
     * @param dfspath
     * @return
     */
    public boolean upload(InputStream in, String dfspath){
        Path p = new Path(dfspath);
        try {
            if(fs.exists(p)){
                System.out.println("The file already exists!");
                return false;
            }
            Progressable progress = new Progressable() {
                //                @Override
                public void progress() {
                    System.out.print(".");
                }
            };

            FSDataOutputStream out = fs.create(p, progress);
            IOUtils.copyBytes(in, out, conf);

            byte[] buffer = new byte[512];
            int length = 0;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * upload hyperspectral an image to HDFS
     * @param in
     * @param id
     * @param blockSize
     * @return
     */
    public String upload(InputStream in, int id, long blockSize, int bands){
//        try {
//            conf.set("dfs.blocksize", "" + blockSize);
//            conf.set("dfs.bytes-per-checksum", "" + bands);
////            conf.set("dfs.client.use.datanode.hostname", "true");
//            String dst = hdfspath + hyperspectralPath + id;
//            System.out.println(dst);
////            conf.set("fs.defaultFS", "hdfs://你的linux主机名:8020");//主机名访问
//            FileSystem fs = FileSystem.get(URI.create(dst), conf);
//            if(fs.exists(new Path(dst))){
//                System.out.println("existed");
//                return "";
//            }
//
//            OutputStream out = fs.create(new Path(dst));
//            IOUtils.copyBytes(in, out, conf);
//
//            out.close();
//            in.close();
//            System.out.println("success");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        // String hdfspath = "hdfs://129.211.73.56:9000" ;
        //String hyperspectralPath = "/images";
        conf = new Configuration();
        System.out.println("111111"+hdfspath);
        //conf.set("fs.defaultFS", hdfspath);
        Path p = new Path(hdfspath + hyperspectralPath + "/" + id);
        File file = new File("/home/hadoop/Downloads/scala-2.11.8.tgz");
//        in = null;
//        try {
//            in = new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        try {
            conf.set("dfs.blocksize", "" + blockSize);
            conf.set("dfs.bytes-per-checksum", "" + bands);
//            fs = FileSystem.get(conf);
            fs=FileSystem.get(new URI(hdfspath),conf,"hadoop");
            if(fs.exists(p)){
                fs.delete(p, true); // 删除
                System.out.println("The file already exists!");
            }
            FSDataOutputStream out = fs.create(p);
            IOUtils.copyBytes(in, out, conf);

            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hyperspectralPath  + id;
    }
    public void uploadHDR(InputStream in, int id){
        Path p = new Path(hyperspectralPath + "/" + id+".hdr");
        System.out.println("path " + hyperspectralPath + "/" + id);
        try {
//            conf.set("dfs.blocksize", "" + blockSize);
//            conf.set("dfs.bytes-per-checksum", "" + bands);
//            fs = FileSystem.get(conf);
            fs=FileSystem.get(new URI(hyperspectralPath + "/" + id+".hdr"),conf,"hadoop");
            if(fs.exists(p)){
                System.out.println("The file already exists!");
//                return false;
            }else{
                mkdir(hyperspectralPath);
            }
            FSDataOutputStream out = fs.create(p);
            IOUtils.copyBytes(in, out, conf);
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return hyperspectralPath + "/" + id;
    }

    public void uploadJars(InputStream in, int id){
        Path p = new Path(hyperspectralPath + "/" + id+".hdr");
        System.out.println("path " + hyperspectralPath + "/" + id);
        try {
//            conf.set("dfs.blocksize", "" + blockSize);
//            conf.set("dfs.bytes-per-checksum", "" + bands);
//            fs = FileSystem.get(conf);
            fs=FileSystem.get(new URI(hyperspectralPath + "/" + id+".hdr"),conf,"hadoop");
            if(fs.exists(p)){
                System.out.println("The file already exists!");
//                return false;
            }else{
                mkdir(hyperspectralPath);
            }
            FSDataOutputStream out = fs.create(p);
            IOUtils.copyBytes(in, out, conf);
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return hyperspectralPath + "/" + id;
    }


    public boolean upload(InputStream in, String dfspath, Envi image){

        Path p = new Path(dfspath);
        try {
//            fs = FileSystem.get(conf);
//            fs.getConf().setLong("fs.local.block.size", blocksize);
            if(fs.exists(p)){
                System.out.println("The file already exists!");
                return false;
            }
//            FSDataOutputStream out = fs.create(p, progress);
//            FSDataOutputStream out = fs.create(p, true, fs.getConf().getInt("io.file.buffer.size", 4096), fs.getDefaultReplication(), blocksize, progress);
            FSDataOutputStream out = fs.create(p, true);
            IOUtils.copyBytes(in, out, conf);

            byte[] buffer = new byte[4096];
            int length = 0;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

}
