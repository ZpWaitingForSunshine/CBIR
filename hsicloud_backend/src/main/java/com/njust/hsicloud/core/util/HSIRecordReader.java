package com.njust.hsicloud.core.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * Created by lyl on 14-12-28.
 */
public class HSIRecordReader extends RecordReader<Integer,byte[]>{
    private FileSplit split;
    private Configuration conf;
    private long start;
    private int length;
    private boolean flag;
    private int key;
    FSDataInputStream fileIn;
    private byte[] data=null;
    @Override
    public void initialize(InputSplit isplit, TaskAttemptContext context) throws IOException, InterruptedException {
        this.split = (FileSplit)isplit;
        flag = true;
        this.conf= context.getConfiguration();

        start = split.getStart();
        length = (int)split.getLength();
        FileSystem fs= split.getPath().getFileSystem(conf);

        key = (int)start;
        fileIn = fs.open(split.getPath());
        fileIn.seek(start);
    }

    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if(flag){
            data = new byte[length];
            fileIn.readFully(start,data,0,length);
            flag = false;
            return true;
        }
        return false;
    }
    @Override
    public Integer  getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    @Override
    public byte[] getCurrentValue() throws IOException, InterruptedException {
        return data;
    }

    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    @Override
    public void close() throws IOException {

    }
}
