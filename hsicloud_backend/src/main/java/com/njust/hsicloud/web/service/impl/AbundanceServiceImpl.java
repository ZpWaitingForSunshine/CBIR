package com.njust.hsicloud.web.service.impl;

import com.njust.hsicloud.web.dao.AbundanceMapper;
import com.njust.hsicloud.web.dao.ImageMapper;
import com.njust.hsicloud.web.dao.SpecdataMapper;
import com.njust.hsicloud.web.model.Abundance;
import com.njust.hsicloud.web.model.AbundanceExample;
import com.njust.hsicloud.web.model.AbundanceKey;
import com.njust.hsicloud.web.model.ImageWithBLOBs;
import com.njust.hsicloud.web.pojo.ImageSpectral;
import com.njust.hsicloud.web.service.AbundanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class AbundanceServiceImpl implements AbundanceService {

    @Autowired
    private AbundanceMapper abundanceMapper;

    @Autowired
    private ImageMapper imageMapper;

    @Autowired
    private SpecdataMapper specdataMapper;

    @Override
    public Abundance selectByPrimaryKey(AbundanceKey key) {
        return abundanceMapper.selectByPrimaryKey(key);
    }

    @Override
    public List<Abundance> selelctByImageId(AbundanceExample abundanceExample) {
        return abundanceMapper.selectByExample(abundanceExample);
    }

    @Override
    public List<ImageSpectral> getImageSpectalInformation(int id) {
        return specdataMapper.retievalSpectralInformation(id);
    }

    public List<Abundance> searchImage(AbundanceExample abundanceExample){
        return abundanceMapper.selectByExample(abundanceExample);
    }

    public List<ImageWithBLOBs> searchHSI(Map<String, Object> map){
        return abundanceMapper.searchHSI(map);
    };

    public int insertAbundances(Map<String, Object> map){
        return abundanceMapper.insertAbundances(map);
    }



}
