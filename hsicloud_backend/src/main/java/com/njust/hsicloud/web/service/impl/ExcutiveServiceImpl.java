package com.njust.hsicloud.web.service.impl;

import com.njust.hsicloud.web.dao.ComponentsMapper;
import com.njust.hsicloud.web.dao.ExcutiveMapper;
import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.Excutive;
import com.njust.hsicloud.web.service.ComponentsService;
import com.njust.hsicloud.web.service.ExcutiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExcutiveServiceImpl implements ExcutiveService {
    @Autowired
    private ExcutiveMapper mapper;
    @Override

    public int insertSelective(Excutive record){ return  mapper.insertSelective(record);}
}
