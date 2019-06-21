package com.njust.cbir.test.service;

import java.util.Date;
import javax.annotation.Resource;

import com.njust.cbir.core.util.ApplicationUtils;
import com.njust.cbir.core.feature.test.TestSupport;
import com.njust.cbir.web.model.User;
import com.njust.cbir.web.service.UserService;

public class UserServiceTest extends TestSupport {

    @Resource
    private UserService userService;

//    @Test
    public void test_insert() {
        User model = new User();
//        model.setUsername("starzou");
        model.setPassword(ApplicationUtils.sha256Hex("123456"));
        model.setCreateTime(new Date());
        userService.insert(model);
    }

//    @Test
    public void test_10insert() {
        for (int i = 0; i < 10; i++) {
            User model = new User();
//            model.setUsername("starzou" + i);
            model.setPassword(ApplicationUtils.sha256Hex("123456"));
            model.setCreateTime(new Date());
            userService.insert(model);
        }
    }

}
