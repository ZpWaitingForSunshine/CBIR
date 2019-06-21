package com.njust.cbir.web.dao;

import com.njust.cbir.web.model.Components;
import com.njust.cbir.web.model.ComponentsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ComponentsMapper {
    int countByExample(ComponentsExample example);

    int deleteByExample(ComponentsExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Components record);

    int insertSelective(Components record);

    List<Components> selectByExample(ComponentsExample example);

    Components selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Components record, @Param("example") ComponentsExample example);

    int updateByExample(@Param("record") Components record, @Param("example") ComponentsExample example);

    int updateByPrimaryKeySelective(Components record);

    int updateByPrimaryKey(Components record);

    List<Components> getAllComponents();
}