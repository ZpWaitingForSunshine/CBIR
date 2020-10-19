package com.njust.hsicloud.web.service;

import com.njust.hsicloud.core.generic.GenericService;
import com.njust.hsicloud.web.model.*;

import java.util.List;
import java.util.Map;

public interface ImageService extends GenericService<ImageWithBLOBs, Long> {
    /**
     *
     */

    List<Envi> getAll();
    int insertEnvi(Envi record);
    Envi getByFilename(String filename);

    List<Image> getTopN(int n);

//    int insertTemp(Image image);
    int insert(ImageWithBLOBs image);

    int updateForeignKey(int IID, int HID);
    int updateByPrimaryKeySelective(ImageWithBLOBs image);
    Image selectBykey(Integer id);
    //int insertPPIRes(Map<String,String> res);
    List<Image> getImagesByPage(int n, int offset);
    int getSum();




}
