package com.njust.hsicloud.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.njust.hsicloud.core.generic.GenericDao;
import com.njust.hsicloud.core.generic.GenericServiceImpl;
import com.njust.hsicloud.web.model.Permission;
import com.njust.hsicloud.web.service.PermissionService;

/**
 * 权限Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午12:05:03
 */
@Service
public class PermissionServiceImpl implements PermissionService {


    @Override
    public List<Permission> selectPermissionsByRoleId(Long roleId) {
        return null;
    }

    @Override
    public int insert(Permission permission) {
        return 0;
    }

    @Override
    public int update(Permission permission) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public Permission selectById(Long id) {
        return null;
    }

    @Override
    public Permission selectOne() {
        return null;
    }

    @Override
    public List<Permission> selectList() {
        return null;
    }
}
