package com.njust.hsicloud.core.assist.runc;

import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.service.ComponentsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class sqloperaters {

    @Autowired
    private ComponentsService componentsService;

    public int insertsql(String value1,String value2){
        System.out.println(componentsService);
        int result = componentsService.insertsql(value1,value2);
        System.out.println(result);
        return result;
    }
    public void updatexcutive(String coloum,String value,int id){
        componentsService.updatexcutive(coloum,value,id);
    }
}
