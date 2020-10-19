package com.njust.hsicloud.web.service.impl;

import com.njust.hsicloud.web.dao.ComponentsMapper;
import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.DagConponent;
import com.njust.hsicloud.web.model.StateModel;
import com.njust.hsicloud.web.service.ComponentsService;
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

    public Components getOne(int cid){
        return  mapper.selectByPrimaryKey(cid);
    }

    public String getFlag(String cid) {
        return mapper.getFlag1(cid);
    }
    public void changeFlag(String cid) {
         mapper.changeFlag1(cid);
    }
    public void changeFlag1(String cid) {
        mapper.changeFlag2(cid);
    }
    public List<StateModel> getAllState() {
        return mapper.getAllState();
    }
    public List<DagConponent> getAllComponentsName() {
        return mapper.getAllComponentsName();
    }
    public void saveDagInfo(String ccname,String dag_json) {
         mapper.saveDagInfo(ccname,dag_json);
    }
    public List<DagConponent> getAllComponentsInfo(String ccname) {
        return mapper.getAllComponentsInfo(ccname);
    }
    public int insertsql(String value1,String value2){return mapper.insertsql(value1,value2); }
    public void updatexcutive(String coloum ,String value,int id){mapper.updatexcutive(coloum,value,id); }
    public String getValue(String coloum,int id){return mapper.getValue(coloum,id);}
}
