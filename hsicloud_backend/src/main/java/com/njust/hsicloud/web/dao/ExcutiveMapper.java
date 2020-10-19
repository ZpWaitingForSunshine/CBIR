package com.njust.hsicloud.web.dao;

import com.njust.hsicloud.web.model.Excutive;
import com.njust.hsicloud.web.model.ExcutiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExcutiveMapper {
    int countByExample(ExcutiveExample example);

    int deleteByExample(ExcutiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Excutive record);

    int insertSelective(Excutive record);

    List<Excutive> selectByExample(ExcutiveExample example);

    Excutive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Excutive record, @Param("example") ExcutiveExample example);

    int updateByExample(@Param("record") Excutive record, @Param("example") ExcutiveExample example);

    int updateByPrimaryKeySelective(Excutive record);

    int updateByPrimaryKey(Excutive record);
}