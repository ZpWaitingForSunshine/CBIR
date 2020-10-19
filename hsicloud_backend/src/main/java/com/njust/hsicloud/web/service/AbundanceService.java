package com.njust.hsicloud.web.service;

import com.njust.hsicloud.web.model.*;
import com.njust.hsicloud.web.pojo.ImageSpectral;

import java.util.List;
import java.util.Map;

public interface AbundanceService {
//    List<Abundance> sele
    Abundance selectByPrimaryKey(AbundanceKey key);

    List<Abundance> selelctByImageId(AbundanceExample abundanceExample);

    List<ImageSpectral> getImageSpectalInformation(int id);
//    List<SpecdataWithBLOBs> selectByExampleWithBLOBs(SpecdataExample example);

    List<Abundance> searchImage(AbundanceExample abundanceExample);

    List<ImageWithBLOBs> searchHSI(Map<String, Object> map);

    // insert abundances' records
    int insertAbundances(Map<String, Object> map);
}
