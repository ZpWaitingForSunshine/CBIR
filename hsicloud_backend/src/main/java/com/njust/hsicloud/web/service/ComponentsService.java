package com.njust.hsicloud.web.service;

import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.DagConponent;
import com.njust.hsicloud.web.model.StateModel;

import java.util.List;

public interface ComponentsService {
    List<Components> getAllComponents();
    Components getOne(int cid);
    String getFlag(String cid);
    void changeFlag(String cid);
    void changeFlag1(String cid);
    List<StateModel> getAllState();
    List<DagConponent> getAllComponentsName();
    void saveDagInfo(String ccname,String dag_json);
    List<DagConponent> getAllComponentsInfo(String ccname);
    int insertsql(String value1,String value2);
    void updatexcutive(String coloum,String value,int id);
    String getValue( String coloum,int id);
}
