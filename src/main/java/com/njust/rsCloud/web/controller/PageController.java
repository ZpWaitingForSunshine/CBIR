package com.njust.rsCloud.web.controller;

import net.sf.json.JSONObject;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 视图控制器,返回jsp视图给前端
 * 
 * @author StarZou
 * @since 2014年5月28日 下午4:00:49
 **/
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 获取token
     *
     * @return
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getToken() {
//        session.removeAttribute("userInfo");
//        // 登出操作
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
//        model.addAttribute("error", "用户名或密码错误 ！");
//        return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR).body("Token");

        JSONObject jResult = new JSONObject();
        jResult.put("Admin-Token","admin-vue");

//        System.out.println("访问了token");
//        boolean result = true;
//        //...
//        Map<String, String> map = new HashMap<>();
////        map.put("valid", result);
//        map.put("Admin-Token","admin-vue");
//        jResult.put("ddd",map);
//
//        ObjectMapper mapper = new ObjectMapper();
//        String resultString = "";
//        try {
//            resultString = mapper.writeValueAsString(map);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jResult;

        return jResult.toString();
//        return new ResponseEntity<Object>("Hello World!", HttpStatus.OK);
    }

    /**
     * 登录页
     */
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * dashboard页
     */
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    /**
     * 404页
     */
    @RequestMapping("/404")
    public String error404() {
        return "404";
    }

    /**
     * 401页
     */
    @RequestMapping("/401")
    public String error401() {
        return "401";
    }

    /**
     * 500页
     */
    @RequestMapping("/500")
    public String error500() {
        return "500";
    }

}