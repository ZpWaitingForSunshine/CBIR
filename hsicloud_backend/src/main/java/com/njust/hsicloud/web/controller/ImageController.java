package com.njust.hsicloud.web.controller;

import com.alibaba.fastjson.JSON;
import com.njust.hsicloud.core.entity.JSONResult;
import com.njust.hsicloud.core.util.upload.HDFSOperation;
import com.njust.hsicloud.core.util.upload.HDROperation;
import com.njust.hsicloud.core.util.upload.ImageOperation;
import com.njust.hsicloud.web.model.*;
import com.njust.hsicloud.web.pojo.ImageInsertParam;
import com.njust.hsicloud.web.pojo.ImageSpectral;
import com.njust.hsicloud.web.pojo.ImageUnmixingParam;
import com.njust.hsicloud.web.pojo.ImageUploadParam;
import com.njust.hsicloud.web.service.AbundanceService;
import com.njust.hsicloud.web.service.ImageService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.cloudbus.cloudsim.ParameterException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    @Resource
    private ImageService imageService;

    @Resource
    private AbundanceService abundanceService;

    @Resource
    private HDFSOperation hdfsOperation;

    @Value("${hdfs.path}")
    private String hdfspath;

    @Value("${spark.path}")
    private String sparkpath;

    private int imgId = 0;



    /**
     *  get all HSIs
     *
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String index() {
        JSONObject jResult = new JSONObject();
        jResult.put("data", "ddd" );
        jResult.put("code", 200);
        jResult.put("total", 2);
        return jResult.toString();
    }


    /**
     *  get all HSIs
     *
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getAll() {
//        System.out.println("hello");
        JSONObject jResult = new JSONObject();
        List<Envi> list = imageService.getAll();
        jResult.put("data", list );
        jResult.put("code", 200);
        jResult.put("total", list.size());
        return jResult.toString();
    }

    /**
     * get top N HSIs
     *
     * @param n the number of HSIs wanted
     * @return
     */
    @RequestMapping(value = "/getTopN", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "get first N images", notes = "return first N images' information")
    public @ResponseBody
    JSONResult getTopN(@RequestParam @ApiParam(name = "n", required = true) Integer n) {

        List<Image> list = imageService.getTopN(n);
        return new JSONResult(list, "200", true);
    }

    /**
     * get HSIs list order by page
     *
     * @param n
     * @param offset
     * @return image list
     */
    @ApiOperation(value = "get HSIs by page", notes = "return HSIs from n to n+offset")
    @RequestMapping(value = "/getImageByPage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getImageByPage(@RequestParam(name = "n", required = true) Integer n, @RequestParam(name = "offset", required = true) Integer offset) {
        System.out.println(n);
        System.out.println(offset);
        List<Image> list = imageService.getImagesByPage(n, offset);
        return new JSONResult(list, "200", true);
    }

    /**
     * get sum of images
     *
     * @return
     */
    @RequestMapping(value = "/getSum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getSum() {
        int sum = imageService.getSum();
        return new JSONResult(sum, "200", true);
    }

    /**
     * get detail by HSI id
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getimage", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getImage(@RequestParam("id") Integer id) {
        ImageWithBLOBs imageWithBLOBs = imageService.selectById((long)id);
        return new JSONResult(imageWithBLOBs, "200", true);
    }


    /**
     * upload hdr file
     *
     * @param request
     * @param file
     */
    @RequestMapping(value = "/hdrupload", method = RequestMethod.POST)
    public @ResponseBody
    JSONResult hdrUpload(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename(); // get original
        System.out.println("filename:" + filename);
        long timestamp = System.currentTimeMillis();
        try {
            ImageWithBLOBs image = HDROperation.getImageMeta(file.getInputStream());
            // add filename
            image.setFilename(filename.replace(".hdr", "").replace(".HDR", ""));

            image.setCreatetime(timestamp);
            imageService.insert(image);
            // get image id
            imgId = image.getId();

            // 存储hdr文件
            WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();
            String hdrpath = servletContext.getRealPath("/") + "/hdr/" ;
            if(!new File(hdrpath).exists()){
                new File(hdrpath).mkdirs();
            }
            file.transferTo(new File(hdrpath + image.getId() + ".hdr"));

            System.out.println(imgId);
            return new JSONResult(image, "200", true);
        } catch (IOException  e) {
            e.printStackTrace();
        }
        return new JSONResult(null, "200", true);
    }

    /**
     * upload original filex
     *
     */
    @RequestMapping(value = "imgupload", method = {RequestMethod.POST})
    @ResponseBody
    public JSONResult imgUpload(@RequestParam Map<String, String> requestPara, @RequestParam("file") MultipartFile file) throws IOException {
        Map<String, String> result;
        result = requestPara.entrySet().stream().filter(r -> !r.getValue().equals("null")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        ObjectMapper objectMapper = new ObjectMapper();
        ImageWithBLOBs image = objectMapper.convertValue(result, ImageWithBLOBs.class);
        int dataType = image.getDatatype();
        // set size
//        MultipartFile file = image.getMultipartFile();
        long fileSize = file.getSize();
        int bands = image.getBands();
        image.setSize(fileSize / 1024 / 1024.0);
        // upload
        int blocknumber = image.getBlocknumber(); // get blocknumber
        image.setBlocknumber(blocknumber);
        // calu blocksize
        double blocksize =  Math.ceil(fileSize / blocknumber / dataType / bands) * dataType * bands;
        image.setBlocksize(blocksize);
        // 设置创建时间
        image.setCreatetime(System.currentTimeMillis() / 1000);
        // 然后分开上传
        ImageOperation imageOperation = new ImageOperation();
        // 生成缩略图 并存储
        String thumbnailname = imageOperation.generateThumbnail(file.getInputStream(),image);
        image.setThumbnailurl(thumbnailname);
        // 上传hdfs id blocksize bands file
        String path = hdfsOperation.upload(file.getInputStream(),image.getId(),(long)blocksize,bands);
        image.setHdfsurl(path);
        imageService.updateByPrimaryKeySelective(image);
        return new JSONResult(image, "200", true);
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONResult test(@RequestBody Image image) {
//        System.out.println(i);
        System.out.println("6666666");
        return new JSONResult("ddd", "200", true);
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/getSpecdata", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JSONResult getSpecdata(@RequestBody Integer id) {
//        System.out.println(i);
        AbundanceExample abundanceExample = new AbundanceExample();
        AbundanceExample.Criteria criteriaabundance = abundanceExample.createCriteria();

        SpecdataExample specdataExample = new SpecdataExample();
        SpecdataExample.Criteria criteriaspecdata = specdataExample.createCriteria();

//        criteriaspecdata.andSpeclibidEqualTo(criteriaabundance.andImageidEqualTo(id).getCriteria().add("id"));


        abundanceExample.or().andImageidEqualTo(id);
        List<ImageSpectral> list = abundanceService.getImageSpectalInformation(id);
        System.out.println("6666666");
        return new JSONResult(list, "200", true);
    }


    @RequestMapping(value = "/searchImg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String searchImg(@RequestBody Map<String, String> map) {
        System.out.println("4444444444");
        List<Map<String, String>> list = new ArrayList<>();
        Iterator iterator = map.keySet().iterator(); // key 的迭代器
        while (iterator.hasNext()){
            String key = (String) iterator.next(); // 获取
            System.out.println(key + ":" + map.get(key));
            Map<String, String> map1 = new HashMap<>();
            map1.put("param1",key);
            map1.put("param2",map.get(key));
            list.add(map1);
        }
        Map<String,Object> pMap = new HashMap<>();
        pMap.put("list",list);
        pMap.put("listSize",list.size());
        List<ImageWithBLOBs> list1 = abundanceService.searchHSI(pMap);
        JSONObject jResult = new JSONObject();
        jResult.put("code", 200);
        jResult.put("data", list1);
        return jResult.toString();
    }

    @RequestMapping(value = "/insertAbundances", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String insertAbundances(@RequestBody ImageInsertParam imageInsertParam) {
        System.out.println("insertAbundances");
        List<Map<String, Double>> list = new ArrayList<>();
        Iterator iterator = imageInsertParam.getMap().keySet().iterator(); // key 的迭代器
        while (iterator.hasNext()){
            int key = (int)iterator.next(); // 获取
            System.out.println(key + ":" + imageInsertParam.getMap().get(key));
            Map<String, Double> map1 = new HashMap<>();
            map1.put("specid", key + 0.0);
            map1.put("abundance", imageInsertParam.getMap().get(key));
            list.add(map1);
        }
        Map<String,Object> pMap = new HashMap<>();
        pMap.put("list", list);
        pMap.put("imageid", imageInsertParam.getImageid());
        int list1 = abundanceService.insertAbundances(pMap);
        JSONObject jResult = new JSONObject();
        jResult.put("code", 200);
        jResult.put("data", list1);
        return jResult.toString();
    }

    @RequestMapping(value = "/unmixing", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String unmixing(@RequestBody ImageUnmixingParam imageUnmixingParam) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        //HTTP请求类型创建HttpPost实例
        HttpPost post = new HttpPost( imageUnmixingParam.getMaster() + "/v1/submissions/create");
        //使用addHeader方法添加请求头部,诸如User-Agent, Accept-Encoding等参数.
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        JobSubmitRequest jobSubmitRequest = new JobSubmitRequest();
        jobSubmitRequest.setAction("CreateSubmissionRequest");
        jobSubmitRequest.setClientSparkVersion("2.3.3");

        // params  参数
        List<String> params = new ArrayList<>();
        params.add("http://192.168.1.100:8082");
        params.add(imageUnmixingParam.getImageid() + "");
        params.add(hdfspath);
        params.add("jdbc:mysql://localhost:3306/hsicloud");
        jobSubmitRequest.setAppArgs(params);

        Map<String, String> environment = new HashMap<>();
        environment.put("SPARK_ENV_LOADED", "1");
        jobSubmitRequest.setEnvironmentVariables(environment);

        jobSubmitRequest.setMainClass("njust.chain.index");

        Map<String, String> sparkProperties = new HashMap<>();

        sparkProperties.put("spark.jars", hdfspath + "/unmixing.jar");
        sparkProperties.put("spark.driver.supervise", "false");
        sparkProperties.put("spark.app.name", "unmixing");
        sparkProperties.put("spark.submit.deployMode", "cluster");
        sparkProperties.put("spark.driver.memory", imageUnmixingParam.getDrivermemory() + "");
        sparkProperties.put("spark.executor.cores", imageUnmixingParam.getExecutorcores() + "");
        sparkProperties.put("spark.executor.memory", imageUnmixingParam.getExecutormemory() + "");

        jobSubmitRequest.setSparkProperties(sparkProperties);
        String content = JSON.toJSONString(jobSubmitRequest);
        StringEntity se = new StringEntity(content, "utf-8");

        se.setContentEncoding("UTF-8");
        se.setContentType("application/json");
        post.setEntity(se);
        CloseableHttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        String resData = EntityUtils.toString(response.getEntity(), "utf-8");

        return resData;
    }
}
