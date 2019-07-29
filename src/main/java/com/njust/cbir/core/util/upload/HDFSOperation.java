package com.njust.cbir.core.util.upload;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HDFSOperation {
    private Configuration conf;
    private FileSystem fs;

//    @Value("${hdfs.path}")
    private String hdfspath = "hdfs://10.10.10.64:9000";

//    @Value("${hdfs.hyperspectral}")
    private String hyperspectralPath = "/images/hyperspctral";


    public String getHdfspath() {
        return hdfspath;
    }

    public String getHyperspectralPath() {
        return hyperspectralPath;
    }

    public HDFSOperation(){

        conf = new Configuration();
        System.out.println("111111"+hdfspath);
        conf.set("fs.defaultFS", hdfspath);
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
        Path p = new Path(hyperspectralPath + "/" + id);
        System.out.println("path " + hyperspectralPath + "/" + id);
        try {
            conf.set("dfs.blocksize", "" + blockSize);
            conf.set("dfs.bytes-per-checksum", "" + bands);
//            fs = FileSystem.get(conf);
            fs=FileSystem.get(new URI(hyperspectralPath + "/" + id),conf,"hadoop");
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
        return hyperspectralPath + "/" + id;
    }




}
