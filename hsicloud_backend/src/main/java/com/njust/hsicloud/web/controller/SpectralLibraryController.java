package com.njust.hsicloud.web.controller;


import com.njust.hsicloud.web.model.Specdata;
import com.njust.hsicloud.web.model.SpecdataExample;
import com.njust.hsicloud.web.model.SpecdataWithBLOBs;
import com.njust.hsicloud.web.model.Speclibname;
import com.njust.hsicloud.web.service.SpectralLibraryService;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户控制器
 *
 * @author UnclePenn
 * @since 2019年5月28日 下午3:54:00
 **/
@Controller
@RequestMapping(value = "/spectrallibrary")
@CrossOrigin
public class SpectralLibraryController {

    @Resource
    private SpectralLibraryService spectralLibraryService;

    /**
     *  get all spectral libarires' names
     * @return
     */
    @ApiOperation(value = "get all spectral libarires", notes="return all spectral libarires' names")
    @RequestMapping(value="/getAllSpecLibName",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getSpecLib(){
//        List<String> specLibLi=spectralLibraryService.();
        List<Speclibname> list = spectralLibraryService.selectAll();
        JSONObject jResult = new JSONObject();
        jResult.put("code", 200);
        jResult.put("data",list);
        return jResult.toString();
    }

    /**
     *  get spectral names by lib id
     * @return
     */
    @ApiOperation(value = "get spectral names by lib id", notes="return spectral set")
    @RequestMapping(value="/getSpecByID",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getSpecByID(int id){
        SpecdataExample specdataExample = new SpecdataExample();
        specdataExample.or().andSpeclibidEqualTo(id);
        List<Specdata> list = spectralLibraryService.selectByExample(specdataExample);
       // List<Speclibname> list = spectralLibraryService.selectAll();
        JSONObject jResult = new JSONObject();
        jResult.put("code", 200);
        jResult.put("data",list);
        return jResult.toString();
    }

    /**
     *  get spectral names by lib id
     * @return
     */
    @ApiOperation(value = "get spectral information by lib id", notes="return spectral information")
    @RequestMapping(value="/getSpectralInformationByID",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getSpectralInformationByID(int id){
        SpecdataExample specdataExample = new SpecdataExample();
        specdataExample.or().andIdEqualTo(id);
        System.out.println("id" + id);
        List<SpecdataWithBLOBs> list = spectralLibraryService.selectByExampleWithBLOBs(specdataExample);
        JSONObject jResult = new JSONObject();
        jResult.put("code", 200);
        jResult.put("data",list);
        return jResult.toString();
    }
}
