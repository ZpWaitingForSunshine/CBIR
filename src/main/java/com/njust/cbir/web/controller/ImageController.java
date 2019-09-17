package com.njust.cbir.web.controller;

import javax.annotation.Resource;

import com.njust.cbir.core.entity.JSONResult;
import com.njust.cbir.core.util.upload.HDFSOperation;
import com.njust.cbir.core.util.upload.HDROperation;
import com.njust.cbir.core.util.upload.ImageOperation;
import com.njust.cbir.web.model.Hdfsinfo;
import com.njust.cbir.web.model.Image;
import com.njust.cbir.web.service.HDFSInfoService;
import com.njust.cbir.web.service.ImageService;
import net.sf.json.JSONObject;
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

    @Resource
    private HDFSInfoService hdfsInfoService;

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
        Image image = HDROperation.getImageMeta(file.getInputStream());
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
     * upload original file
     * @param request
     * @param file
     */
    @RequestMapping("imgupload")
    public @ResponseBody
    JSONResult imgUpload(MultipartHttpServletRequest request, @RequestParam("file") MultipartFile file) throws IOException {
        int id = Integer.parseInt(request.getParameter("id")); // id
        int bands = Integer.parseInt(request.getParameter("bands")); // bands
        int samples = Integer.parseInt(request.getParameter("samples")); // samples
        int rows = Integer.parseInt(request.getParameter("rows")); // samples
        String interleave = request.getParameter("interleave"); // bsq bip bil
        int dataType = Integer.parseInt(request.getParameter("datatype"));
        int datatype = HDROperation.datatype2Bit(Integer.parseInt(request.getParameter("datatype"))); // datatype
        ImageOperation imageOperation = new ImageOperation();
        long fileSize = file.getSize();
        long blockSize = imageOperation.getBlockSize(bands, datatype, fileSize);
        int blockNumber = imageOperation.getBlockNumber(blockSize, fileSize);

//        imageOperation.upload2HDFS(file.getInputStream(),blockSize,id,bands);
        HDFSOperation hdfsOperation = new HDFSOperation();
        hdfsOperation.upload(file.getInputStream(), id, blockSize, bands);
        // image's hdfs url
        String url = hdfsOperation.getHdfspath() + hdfsOperation.getHyperspectralPath() + "/" + id;

        Hdfsinfo hdfsinfo = new Hdfsinfo();
        hdfsinfo.setBlocknumber(blockNumber);
        hdfsinfo.setBlocksize(blockSize);
        hdfsinfo.setUrl(url);
        hdfsinfo.setTime("" + System.currentTimeMillis() / 1000);

        hdfsInfoService.insert(hdfsinfo);
        int HID = hdfsinfo.getId();
        imageService.updateForeignKey(id, HID);

        Image image = new Image();
        image.setId(id);
        image.setHdfsid(HID);
        image.setBands((short) bands);
        image.setRows((short) rows);
        image.setSamples((short) samples);
        image.setInterleave(interleave);
        image.setDatatype((byte) dataType);

        // generate image thumbnail
        String thumbnailUrl = imageOperation.generateThumbnail(file.getInputStream(),image,request);
        image.setThumbnailurl(thumbnailUrl);

        // update image information
        imageService.updateByPrimaryKeySelective(image);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("hdfsurl", url);
        jsonObject.put("thumbnailurl", thumbnailUrl);
        jsonObject.put("hdfsid",HID);

        System.out.println("blocknumber " + blockNumber);
        System.out.println("blockSize " + blockSize);


        // save in hdfs
        //
        return new JSONResult(jsonObject,200,true);
    }



    @RequestMapping(value = "/imgupdate",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult imgUpdate(@ModelAttribute("image") Image image){
        imageService.updateByPrimaryKeySelective(image);
        System.out.println("hello world");
        return new JSONResult("ddd",200,true);
    }

    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/getimage",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getImage(@RequestParam("id") Integer id){
//        int id =Integer.parseInt(params.get("id"));
        Image image = imageService.selectBykey(id);
        return new JSONResult(image,200,true);
    }
}
