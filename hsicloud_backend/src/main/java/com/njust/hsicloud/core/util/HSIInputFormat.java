package com.njust.hsicloud.core.util;

import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import java.io.IOException;

/**
 * Created by lyl on 14-12-28.
 */
public class HSIInputFormat extends FileInputFormat<Integer,byte[]>{

    @Override
    public RecordReader<Integer, byte[]> createRecordReader(InputSplit split, TaskAttemptContext context)
            throws IOException, InterruptedException {
        return new HSIRecordReader();
    }
}
