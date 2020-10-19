package com.njust.hsicloud.web.service.impl;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.njust.hsicloud.web.dao.EnviMapper;
import com.njust.hsicloud.web.dao.ImageMapper;
import com.njust.hsicloud.web.model.*;
import com.njust.hsicloud.web.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限Service实现类
 *
 * @author StarZou
 * @since 2014年6月10日 下午12:05:03
 */
@Service
public class ImageServiceImpl implements ImageService {


    @Autowired
    private ImageMapper imageMapper;


    @Resource
//    private PermissionMapper permissionMapper;
    private EnviMapper enviMapper;

    public List<Envi> getAll(){
        return enviMapper.getAllEnvi();
    }


    public int insertEnvi(Envi record){
        return enviMapper.insert(record);
//        return 1;
    }

    public Envi getByFilename(String filename){
//        return null;
        return enviMapper.getByFilename(filename).get(0);
    }


    @Override
    public int updateForeignKey(int IID, int HID) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(ImageWithBLOBs image) {
        return imageMapper.updateByPrimaryKeySelective((ImageWithBLOBs) image);
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public ImageWithBLOBs selectById(Long id) {
        ImageWithBLOBs imageWithBLOBs = imageMapper.selectByPrimaryKey(Integer.parseInt(id.toString()));
        return imageWithBLOBs;
    }

    @Override
    public ImageWithBLOBs selectOne() {
        return null;
    }

    @Override
    public List<ImageWithBLOBs> selectList() {
        return null;
    }


    public List<Image> getTopN(int n) {
//        return imageMapper.selectTopN(n);
        return null;
    }

    @Override
    public int insert(ImageWithBLOBs image) {
        return imageMapper.insert(image);
    }

    @Override
    public int update(ImageWithBLOBs imageWithBLOBs) {
        return imageMapper.updateByPrimaryKeyWithBLOBs(imageWithBLOBs);
    }

//    public int insertTemp(Image image){
//        return imageMapper.insertTemp(image);
//    }


    public Image selectBykey(Integer id){
        return imageMapper.selectByPrimaryKey(id);
    }

    public List<Image> getImagesByPage(int n, int offset) {
        return imageMapper.selectByOffset(n,offset);
    }

    public int getSum() {
        return imageMapper.getSum();
    }


}
