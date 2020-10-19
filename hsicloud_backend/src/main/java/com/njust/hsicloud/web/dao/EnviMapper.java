package com.njust.hsicloud.web.dao;

import com.njust.hsicloud.web.model.Envi;
import com.njust.hsicloud.web.model.EnviExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnviMapper {
    int countByExample(EnviExample example);

    int deleteByExample(EnviExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Envi record);

    int insertSelective(Envi record);

    List<Envi> selectByExample(EnviExample example);

    Envi selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Envi record, @Param("example") EnviExample example);

    int updateByExample(@Param("record") Envi record, @Param("example") EnviExample example);

    int updateByPrimaryKeySelective(Envi record);

    int updateByPrimaryKey(Envi record);

    List<Envi> getAllEnvi();

    List<Envi> getByFilename(String filename);
}