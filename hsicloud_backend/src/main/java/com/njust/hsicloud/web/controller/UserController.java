package com.njust.hsicloud.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.njust.hsicloud.web.service.ImageService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.njust.hsicloud.web.model.User;
import com.njust.hsicloud.web.service.UserService;

import java.io.File;
import java.util.*;

/**
 * 用户控制器
 * 
 * @author UnclePenn
 * @since 2019年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {


    @Resource
    private UserService userService;

    @Resource
    private ImageService imageService;

    /**
     * 获取token
     *
     * @return
     */
    @RequestMapping(value = "/getToken", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getToken() {
        JSONObject jResult = new JSONObject();
        Map<String, String> map = new HashMap<>();
        map.put("Admin-Token","admin-vue");
        jResult.put("code","200");
        jResult.put("data",map);
        return jResult.toString();
    }



    @RequestMapping(value = "/index",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String index() {
        JSONObject jResult = new JSONObject();
        Map<String, String> map = new HashMap<>();
        map.put("Admin-Token","admin-vue");
        jResult.put("data",map);
        return jResult.toString();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    String getInfo() {
        JSONObject jResult = new JSONObject();
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("admin");
        jResult.put("roles",arrayList);
        jResult.put("name","admin");
        jResult.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        jResult.put("introduction","我也很着急啊");
        JSONObject jResult2 = new JSONObject();
        jResult2.put("data",jResult);
        jResult2.put("code",200);
        return jResult2.toString();
    }




    /**
     * 获取光谱库信息
     * @return
     */
    @RequestMapping("/libsName")
    public @ResponseBody
    String libsName() {
        String tPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String path = tPath.substring(0, tPath.length() - 16) + "app/specLibs/";
        path = path.replace("%20" , " "); // 替换空格
        File[] files = (new File(path)).listFiles();
        JSONObject jResult = new JSONObject();
        for(File f : files) {
            if (f.isDirectory()) {
                File[] subFiles = f.listFiles();
                ArrayList<String> fnamelist = new ArrayList<>();
                for (File sf : subFiles) {
                    String t = sf.getName();
                    if (t.endsWith("hdr")) {
                        fnamelist.add(t.substring(0, t.length() - 4));
                    }
                }
                Collections.sort(fnamelist);
                jResult.put(f.getName(), fnamelist);
            }
        }
        return jResult.toString();
    }


    /**
     * 用户登录
     * 
     * @param user
     * @param result
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, BindingResult result, Model model, HttpServletRequest request) {
        return "login";
    }

    /**
     * 用户登出
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
//        session.removeAttribute("userInfo");
//        // 登出操作
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        return "login";
    }

    /**
     * 基于角色 标识的权限控制案例
     */
    @RequestMapping(value = "/admin")
    @ResponseBody
//    @RequiresRoles(value = RoleSign.ADMIN)
    public String admin() {
        return "拥有admin角色,能访问";
    }

    /**
     * 基于权限标识的权限控制案例
     */
    @RequestMapping(value = "/create")
    @ResponseBody
//    @RequiresPermissions(value = PermissionSign.USER_CREATE)
    public String create() {
        return "拥有user:create权限,能访问";
    }
}
