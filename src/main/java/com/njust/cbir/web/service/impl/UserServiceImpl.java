package com.njust.cbir.web.service.impl;

import java.util.List;

import com.njust.cbir.web.dao.UserMapper;
import org.springframework.stereotype.Service;
import com.njust.cbir.web.model.User;
import com.njust.cbir.web.service.UserService;

import javax.annotation.Resource;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class UserServiceImpl  implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User authentication(User user) {
        return null;
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByName(username);
    }


    @Override
    public int insert(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public User selectById(Long id) {
        return null;
    }

    @Override
    public User selectOne() {
        return null;
    }

    @Override
    public List<User> selectList() {
        return null;
    }

}
