package com.njust.cbir.web.service.impl;

import com.njust.cbir.web.dao.HdfsinfoMapper;
import com.njust.cbir.web.dao.ImageMapper;
import com.njust.cbir.web.model.Hdfsinfo;
import com.njust.cbir.web.model.Image;
import com.njust.cbir.web.service.HDFSInfoService;
import com.njust.cbir.web.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HDFSInfoServiceImpl implements HDFSInfoService {

    @Autowired
    HdfsinfoMapper hdfsinfoMapper;

    @Override
    public int insert(Hdfsinfo hdfsinfo) {
        return hdfsinfoMapper.insert(hdfsinfo);
    }

    @Override
    public int update(Hdfsinfo hdfsinfo) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public Hdfsinfo selectById(Long id) {
        return null;
    }

    @Override
    public Hdfsinfo selectOne() {
        return null;
    }

    @Override
    public List<Hdfsinfo> selectList() {
        return null;
    }

    public Hdfsinfo get(int id){
        return hdfsinfoMapper.selectByPrimaryKey(id);
    }
}
