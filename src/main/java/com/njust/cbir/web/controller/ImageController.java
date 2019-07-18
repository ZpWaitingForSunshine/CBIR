package com.njust.cbir.web.controller;

import javax.annotation.Resource;

import com.njust.cbir.core.entity.JSONResult;
import com.njust.cbir.core.util.upload.HDRUpload;
import com.njust.cbir.web.model.Image;
import com.njust.cbir.web.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 用户控制器
 * 
 * @author UnclePenn
 * @since 2019年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/image")
@CrossOrigin
public class ImageController {


    @Resource
    private ImageService imageService;

    /**
     * get top N images
     *
     * @param n the number of images wanted
     *
     * @return
     */
    @RequestMapping(value = "/getTopN",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getTopN(@RequestParam(name = "n", required = true) Integer n) {
        System.out.println(n);
//        List<Image> list = imageService.getTopN(n);
        return new JSONResult("ddd",200,true);
    }

    /**
     * upload hdr file
     * @param request
     * @param file
     */
    @RequestMapping("/hdrupload")
    public @ResponseBody
    JSONResult hdrUpload(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {

        String filename = file.getOriginalFilename(); // get original
        String timestamp = String.valueOf(System.currentTimeMillis());
        String path =  "/home/hadoop/Documents/Files/";
        Image image = HDRUpload.getImageMeta(file.getInputStream());
        // add filename
        image.setFilename(filename);
        imageService.insertTemp(image);
        // get image id
        int id = image.getId();
        System.out.println(id);
        File newFile;
        newFile = new File(path + timestamp + filename);
        if(!newFile.exists())
            newFile.createNewFile();
//                // 将内存中的数据写入磁盘gv
        file.transferTo(newFile);
        return new JSONResult(image,200,true);
    }

    /**
     * upload hdr file
     * @param request
     * @param file
     */
    @RequestMapping("imgupload")
    public @ResponseBody
    JSONResult imgUpload(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {

        // change the
        // save in hdfs
        //
        return new JSONResult("dd",200,true);
    }



//    /**
//     *
//     * @param n
//     * @return
//     */
//    @RequestMapping(value = "/getPage",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
//    public @ResponseBody
//    JSONResult getPage(@RequestParam(name = "n", required = true) Integer n) {
//        System.out.println(n);
//        List<Image> list = imageService.getTopN(n);
//        JSONObject jResult = new JSONObject();
//        Map<String, String> map = new HashMap<>();
//        map.put("Admin-Token","admin-vue");
//        jResult.put("data",map);
//        return new JSONResult(list,200,true);
//    }










}
