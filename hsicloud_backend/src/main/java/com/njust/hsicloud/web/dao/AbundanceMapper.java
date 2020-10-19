package com.njust.hsicloud.web.dao;

import com.njust.hsicloud.web.model.Abundance;
import com.njust.hsicloud.web.model.AbundanceExample;
import com.njust.hsicloud.web.model.AbundanceKey;
import java.util.List;
import java.util.Map;

import com.njust.hsicloud.web.model.ImageWithBLOBs;
import org.apache.ibatis.annotations.Param;

public interface AbundanceMapper {
    int countByExample(AbundanceExample example);

    int deleteByExample(AbundanceExample example);

    int deleteByPrimaryKey(AbundanceKey key);

    int insert(Abundance record);

    int insertSelective(Abundance record);

    List<Abundance> selectByExample(AbundanceExample example);

    Abundance selectByPrimaryKey(AbundanceKey key);

    int updateByExampleSelective(@Param("record") Abundance record, @Param("example") AbundanceExample example);

    int updateByExample(@Param("record") Abundance record, @Param("example") AbundanceExample example);

    int updateByPrimaryKeySelective(Abundance record);

    int updateByPrimaryKey(Abundance record);

    List<ImageWithBLOBs> searchHSI(Map<String, Object> queryList);

    int insertAbundances(Map<String, Object> insertList);
}