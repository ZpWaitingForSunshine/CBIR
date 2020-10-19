package com.njust.hsicloud.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.njust.hsicloud.core.generic.GenericDao;
import com.njust.hsicloud.core.generic.GenericServiceImpl;
import com.njust.hsicloud.web.model.Role;
import com.njust.hsicloud.web.service.RoleService;

/**
 * 角色Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午4:16:33
 */
@Service
public class RoleServiceImpl implements RoleService {


    @Override
    public List<Role> selectRolesByUserId(Long userId) {
        return null;
    }

    @Override
    public int insert(Role role) {
        return 0;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public Role selectById(Long id) {
        return null;
    }

    @Override
    public Role selectOne() {
        return null;
    }

    @Override
    public List<Role> selectList() {
        return null;
    }
}
