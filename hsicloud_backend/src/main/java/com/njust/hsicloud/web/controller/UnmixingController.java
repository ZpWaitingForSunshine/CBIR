package com.njust.hsicloud.web.controller;

import com.alibaba.fastjson.JSON;
import com.github.ywilkof.sparkrestclient.FailedSparkRequestException;
import com.github.ywilkof.sparkrestclient.SparkRestClient;
import com.njust.hsicloud.web.model.JobSubmitRequest;
import com.njust.hsicloud.web.pojo.SparkSubmitParam;
import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(value = "/unmixing")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class UnmixingController {

    @Value("${spark.path}")
    private String sparkpath;

    @Value("${hdfs.path}")
    private String hdfspath;



    /**
     *
     *  get all HSIs
     *
     */
    @RequestMapping(value = "/submitTask", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String submitTask(@RequestBody SparkSubmitParam sparkSubmitParam) throws IOException, FailedSparkRequestException {

        String jars = "hdfs://10.10.10.110:9000/PPI_SAD_SCLS.jar";
        JSONObject jResult = new JSONObject();

        if(sparkSubmitParam.getAbundance().equals("scls") && sparkSubmitParam.getCompare().equals("sad") && sparkSubmitParam.getExtraction().equals("ppi")){

            String[] argss = {"http://10.10.10.110:8081", sparkSubmitParam.getImageid()+"",  "hdfs://10.10.10.110:9000", "jdbc:mysql://10.10.10.116:3306/hsicloud"};

            final Map<String,String> environmentVariables = new HashMap<>();
            environmentVariables.put("spark.driver.memory",sparkSubmitParam.getDrivermemory() + "g");
            environmentVariables.put("spark.executor.cores",sparkSubmitParam.getExecutorcores()+"");
            environmentVariables.put("spark.executor.memory",sparkSubmitParam.getExecutormemory()+"g");

            SparkRestClient sparkRestClient = SparkRestClient.builder()
                    .masterHost("10.10.10.110")
                    .sparkVersion("2.3.3")
                    .build();
            final String submissionId = sparkRestClient.prepareJobSubmit()
                    .appName("MySparkJob!")
                    .appResource("hdfs://10.10.10.110:9000/PPI_SAD_SCLS.jar")
                    .appArgs(Arrays.asList(argss.clone()))
                    .mainClass("njust.chain.index")
                    .withProperties()
                    .put("spark.driver.memory",sparkSubmitParam.getDrivermemory() + "g")
                    .put("spark.executor.cores",sparkSubmitParam.getExecutorcores()+"")
                    .put("spark.executor.memory",sparkSubmitParam.getExecutormemory()+"g")
                    .submit();
            System.out.println(submissionId);
            jResult.put("data", submissionId);

        }

        jResult.put("code", 200);
        jResult.put("total", 2);

        return jResult.toString();
    }
}
