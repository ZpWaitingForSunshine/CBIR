package com.njust.cbir.web.service.impl;

import com.njust.cbir.web.dao.ImageMapper;
import com.njust.cbir.web.dao.TokenMapper;
import com.njust.cbir.web.model.Image;
import com.njust.cbir.web.model.Token;
import com.njust.cbir.web.service.ImageService;
import com.njust.cbir.web.service.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public int insert(Image image) {
        return 0;
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

    @Override
    public List<Image> getTopN(int n) {
        return imageMapper.selectTopN(n);
    }
}
