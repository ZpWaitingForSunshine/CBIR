package com.njust.rsCloud.web.controller;

import com.njust.rsCloud.web.model.Components;
import com.njust.rsCloud.web.service.ComponentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/components")
public class ComponentsController {
    @Autowired
    private ComponentsService componentsService;

    @GetMapping
    @ResponseBody
    public List<Components> getComponentsByParam(Map<String,Object> param ){
        return componentsService.getAllComponents();
    }
}
