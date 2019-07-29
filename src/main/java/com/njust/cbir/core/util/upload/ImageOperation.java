package com.njust.cbir.core.util.upload;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.InputStream;

public class ImageOperation {

//    @Value("${spark.cores}")
    private int sparkCores = 32;

//    @Value("${spark.partitions}")
    private int partitions = 2;


    @Autowired
    private HDFSOperation hdfsOperation;


    /**
     * generate thumbnail
     */
    public void generateThumbnail(){

    }

    /**
     * entry of spectral
     * get number of pixels in per block
     * @param bands
     * @param datatype
     * @return
     */
    public long getBlockSize(int bands, int datatype, long fileSize){

        int NumberOfPixels = (int) Math.floor(fileSize / bands / datatype / sparkCores / partitions );
        return NumberOfPixels * bands * datatype;
    }

    /**
     * get the number of file blocks according spark cores
     * @param
     * @param fileSize
     * @return
     */
    public int getBlockNumber(long blockSize,long fileSize){
        return (int) (fileSize / blockSize);
    }


    public String upload2HDFS(InputStream in, long blockSize, int id ){

        hdfsOperation = new HDFSOperation();
//        String hdfsurl = hdfsOperation.upload(in,id,blockSize);
        return null;
    }
}
