package com.njust.cbir.web.controller;


import com.njust.cbir.core.entity.JSONResult;
import com.njust.cbir.web.model.Hdfsinfo;
import com.njust.cbir.web.service.HDFSInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * hdfs controller
 *
 * @author UnclePenn
 * @since 2019年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/hdfs")
@CrossOrigin
public class HDFSController {

    @Resource
    private HDFSInfoService hdfsInfoService;

    @RequestMapping(value = "/get",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody
    JSONResult getTopN(@RequestParam(name = "id", required = true) Integer id) {
        if(id == null)
            return  new JSONResult("",200,true);
        Hdfsinfo h =  hdfsInfoService.get(id);
        return new JSONResult(h,200,true);
    }
}
