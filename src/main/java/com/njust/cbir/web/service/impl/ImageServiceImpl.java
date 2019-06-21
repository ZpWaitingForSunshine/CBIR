package com.njust.cbir.web.service.impl;

import java.util.List;
import javax.annotation.Resource;

import com.njust.cbir.web.dao.EnviMapper;
import com.njust.cbir.web.model.Envi;
import com.njust.cbir.web.model.Role;
import com.njust.cbir.web.service.ImageService;
import org.springframework.stereotype.Service;

/**
 * 权限Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午12:05:03
 */
@Service
public class ImageServiceImpl implements ImageService {

    @Resource
//    private PermissionMapper permissionMapper;
    private EnviMapper enviMapper;

    public List<Envi> getAll(){
        return enviMapper.getAllEnvi();
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

    public int insertEnvi(Envi record){
        return enviMapper.insert(record);
//        return 1;
    }

    public Envi getByFilename(String filename){
//        return null;
        return enviMapper.getByFilename(filename).get(0);
    }
}
