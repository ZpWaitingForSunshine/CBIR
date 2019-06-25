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

//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public int insert(User model) {
//        return userMapper.insertSelective(model);
//    }
//
//    @Override
//    public int update(User model) {
//        return userMapper.updateByPrimaryKeySelective(model);
//    }
//
//    @Override
//    public int delete(Long id) {
//        return userMapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public User authentication(User user) {
//        return userMapper.authentication(user);
//    }
//
//    @Override
//    public User selectById(Long id) {
//        return userMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public GenericDao<User, Long> getDao() {
//        return userMapper;
//    }
//
//    @Override
//    public User selectByUsername(String username) {
//        UserExample example = new UserExample();
//        example.createCriteria().andUsernameEqualTo(username);
//        final List<User> list = userMapper.selectByExample(example);
//        return list.get(0);
//    }

}
