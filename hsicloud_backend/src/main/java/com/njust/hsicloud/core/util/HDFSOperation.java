package com.njust.hsicloud.core.util;


import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import com.njust.hsicloud.web.model.Envi;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

//import com.cbirDemo.bean.Image;

public class HDFSOperation {
    private Configuration conf;
    private FileSystem fs;
    public HDFSOperation() throws IOException {
        conf = new Configuration();
        String pathtemp = "hdfs://10.10.10.96:9000";
//        conf.addResource(new Path("core-site.xml"));
//        fs = FileSystem.get(conf);
        conf.set("fs.defaultFS", pathtemp);
        try {
            fs=FileSystem.get(new URI(pathtemp),conf,"hadoop");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
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
    public String getDfsPath(){
        return conf.get("fs.defaultFS") + "/CBIRDemo/rice/";
    }
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