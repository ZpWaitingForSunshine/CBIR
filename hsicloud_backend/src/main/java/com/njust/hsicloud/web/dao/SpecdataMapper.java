package com.njust.hsicloud.web.dao;

import com.njust.hsicloud.web.model.Specdata;
import com.njust.hsicloud.web.model.SpecdataExample;
import com.njust.hsicloud.web.model.SpecdataWithBLOBs;
import java.util.List;

import com.njust.hsicloud.web.pojo.ImageSpectral;
import org.apache.ibatis.annotations.Param;

public interface SpecdataMapper {
    int countByExample(SpecdataExample example);

    int deleteByExample(SpecdataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SpecdataWithBLOBs record);

    int insertSelective(SpecdataWithBLOBs record);

    List<SpecdataWithBLOBs> selectByExampleWithBLOBs(SpecdataExample example);

    List<Specdata> selectByExample(SpecdataExample example);

    SpecdataWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SpecdataWithBLOBs record, @Param("example") SpecdataExample example);

    int updateByExampleWithBLOBs(@Param("record") SpecdataWithBLOBs record, @Param("example") SpecdataExample example);

    int updateByExample(@Param("record") Specdata record, @Param("example") SpecdataExample example);

    int updateByPrimaryKeySelective(SpecdataWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SpecdataWithBLOBs record);

    int updateByPrimaryKey(Specdata record);

    // 通过id去找图片信息
    List<ImageSpectral> retievalSpectralInformation(int id);
}