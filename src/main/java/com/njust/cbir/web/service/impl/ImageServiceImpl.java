package com.njust.cbir.web.service.impl;

import com.njust.cbir.web.dao.ImageMapper;
import com.njust.cbir.web.model.Image;
import com.njust.cbir.web.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public int insert(Image image) {
        return imageMapper.insert(image);
    }

    @Override
    public int update(Image image) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public Image selectById(Long id) {
        return null;
    }

    @Override
    public Image selectOne() {
        return null;
    }

    @Override
    public List<Image> selectList() {
        return null;
    }


    public List<Image> getTopN(int n) {
//        return imageMapper.selectTopN(n);
        return null;
    }

    public int insertTemp(Image image){
        return imageMapper.insertTemp(image);
    }

    public int updateForeignKey(int IID, int HID){
        return imageMapper.updateHDFSInfo(IID, HID);
    }

    public int updateByPrimaryKeySelective(Image image){
        return  imageMapper.updateByPrimaryKeySelective(image);
    }

    public Image selectBykey(Integer id){
        return imageMapper.selectByPrimaryKey(id);
    }
}
