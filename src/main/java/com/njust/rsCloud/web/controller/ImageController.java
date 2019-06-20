package com.njust.rsCloud.web.controller;

import com.njust.rsCloud.web.model.Envi;
import com.njust.rsCloud.web.service.ImageService;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/image")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {

    @Resource
    private ImageService imageService;

    /**
     * upload images
     *
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST, produces = "application/jsonp;charset=UTF-8")
    public @ResponseBody
    String upLoad(@RequestParam("file") CommonsMultipartFile file)
            throws IllegalStateException, IOException{

        //用来检测程序运行时间
        long  startTime=System.currentTimeMillis();
        System.out.println("fileName："+file.getOriginalFilename());

        try {
            //获取输出流
            OutputStream os=new FileOutputStream("~/"+new Date().getTime()+file.getOriginalFilename());
            //获取输入流 CommonsMultipartFile 中可以直接得到文件的流
            InputStream is=file.getInputStream();
            int temp;
            //一个一个字节的读取并写入
            while((temp=is.read())!=(-1))
            {
                os.write(temp);
            }
            os.flush();
            os.close();
            is.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long  endTime=System.currentTimeMillis();
        System.out.println("方法一的运行时间："+String.valueOf(endTime-startTime)+"ms");
        return "success";


    }


    @RequestMapping(value = "/statement", method = RequestMethod.POST)
    @ResponseBody
    public String uploadStatement(DefaultMultipartHttpServletRequest request) {

        //上传中用到的流
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        byte[] bytes = new byte[2048];
        int n = -1;


        String fileName = "e.img";

        return "json";
    }
    /**
     * get image list
     *
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getAll() {
        System.out.println("hello");
        JSONObject jResult = new JSONObject();
        List<Envi> list = imageService.getAll();
        jResult.put("data", list );
        jResult.put("code", 200);
        jResult.put("total", list.size());
        return jResult.toString();
    }


    /**
     * 上传文件
     */
    @PostMapping("/upload1")
    @RequiresPermissions("basedata:oesmembers:upload")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("companyId") Integer companyId) {
        System.out.println(companyId);

        //上传文件 相关逻辑

        return "ok";
    }

    /**
     *
     */

    @RequestMapping(value = "/addImage", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @PostMapping("addImage")
    public @ResponseBody
    String addImage(@RequestParam("file") CommonsMultipartFile[] files, HttpServletRequest request){
//        request.setHeader('access-Control-Allow-Origin:*');
        for(int i = 0;i<files.length;i++){
            System.out.println("fileName---------->" + files[i].getOriginalFilename());

            if(!files[i].isEmpty()){
                int pre = (int) System.currentTimeMillis();
                try {
                    //拿到输出流，同时重命名上传的文件
                    FileOutputStream os = new FileOutputStream("~/" + new Date().getTime() + files[i].getOriginalFilename());
                    //拿到上传文件的输入流
                    FileInputStream in = (FileInputStream) files[i].getInputStream();

                    //以写字节的方式写文件
                    int b = 0;
                    while((b=in.read()) != -1){
                        os.write(b);
                    }
                    os.flush();
                    os.close();
                    in.close();
                    int finaltime = (int) System.currentTimeMillis();
                    System.out.println(finaltime - pre);

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("上传出错");
                }
            }
        }
        return "/success";
    }

}
