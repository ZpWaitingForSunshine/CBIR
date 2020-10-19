package com.njust.hsicloud.web.dao;


import com.njust.hsicloud.web.model.Speclibname;
import com.njust.hsicloud.web.model.SpeclibnameExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SpeclibnameMapper {
    int countByExample(SpeclibnameExample example);

    int deleteByExample(SpeclibnameExample example);

    int insert(Speclibname record);

    int insertSelective(Speclibname record);

    List<Speclibname> selectByExample(SpeclibnameExample example);

    int updateByExampleSelective(@Param("record") Speclibname record, @Param("example") SpeclibnameExample example);

    int updateByExample(@Param("record") Speclibname record, @Param("example") SpeclibnameExample example);

    List<Speclibname> selectAll();
}