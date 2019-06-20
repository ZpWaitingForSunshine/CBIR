package com.njust.rsCloud.web.service.impl;

import com.njust.rsCloud.web.dao.ComponentsMapper;
import com.njust.rsCloud.web.model.Components;
import com.njust.rsCloud.web.service.ComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ComponentsServiceImpl implements ComponentsService {
    @Autowired
    private ComponentsMapper mapper;
    @Override
    public List<Components> getAllComponents() {
        return mapper.getAllComponents();
    }
}
