package com.njust.cbir.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.njust.cbir.core.entity.JSONResult;
import com.njust.cbir.web.model.Image;
import com.njust.cbir.web.model.Token;
import com.njust.cbir.web.model.User;
import com.njust.cbir.web.service.ImageService;
import com.njust.cbir.web.service.TokenService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.njust.cbir.web.service.UserService;

import java.io.File;
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

    @RequestMapping(value = "/getTopN",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getTopN(@RequestParam(name = "n", required = true) Integer n) {
        System.out.println(n);
        List<Image> list = imageService.getTopN(n);
        JSONObject jResult = new JSONObject();
        Map<String, String> map = new HashMap<>();
        map.put("Admin-Token","admin-vue");
        jResult.put("data",map);
        return new JSONResult(list,200,true);
    }



}
