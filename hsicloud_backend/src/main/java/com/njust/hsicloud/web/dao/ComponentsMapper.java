package com.njust.hsicloud.web.dao;

import com.njust.hsicloud.web.model.Components;
import com.njust.hsicloud.web.model.ComponentsExample;
import java.util.List;

import com.njust.hsicloud.web.model.DagConponent;
import com.njust.hsicloud.web.model.StateModel;
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
    String getFlag1(@Param("cid") String cid);
    void changeFlag1(@Param("cid") String cid);
    void changeFlag2(@Param("cid") String cid);
    List<StateModel> getAllState();
    List<DagConponent> getAllComponentsName();
    void saveDagInfo(@Param("ccname") String ccname,@Param("dag_json") String dag_json);
    List<DagConponent> getAllComponentsInfo(@Param("ccname")  String ccname);
    int insertsql(@Param("value1") String value1,@Param("value2") String value2);
    void updatexcutive(@Param("coloum") String coloum,@Param("value") String value,@Param("id") Integer id);
    String getValue(@Param("coloum") String coloum,@Param("id") Integer id);
}