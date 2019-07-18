package com.njust.cbir.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.njust.cbir.core.entity.JSONResult;
import com.njust.cbir.web.model.Token;
import com.njust.cbir.web.model.User;
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
@RequestMapping(value = "/user")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    /**
     * user get token
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult login(@RequestBody Map<String,String> userinfo) {
        String name = userinfo.get("username");
        String password = userinfo.get("password");

        System.out.println(name + " " + password);
        // verity login
        User user = userService.selectByUsername(name);

        // insert token
        String tokenSerial = "cbir" + name;
        Token token = new Token();
        token.setToken(tokenSerial);
        token.setUid(Math.toIntExact(user.getId()));
        int success = tokenService.insert(token);

        Map<String, String> map = new HashMap<>();

        map.put("token",tokenSerial);
        return new JSONResult(map,200,true);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info",method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getInfo(@RequestBody Map<String,String> token) {
        // verify token and get the uid ,then get userinfo
        // demo
        User user = userService.selectByUsername("admin");
        return new JSONResult(user,200,true);
    }


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
