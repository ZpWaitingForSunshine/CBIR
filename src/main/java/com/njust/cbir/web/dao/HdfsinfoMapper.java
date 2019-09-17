package com.njust.cbir.web.dao;

import com.njust.cbir.web.model.Hdfsinfo;
import com.njust.cbir.web.model.HdfsinfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HdfsinfoMapper {
    int countByExample(HdfsinfoExample example);

    int deleteByExample(HdfsinfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Hdfsinfo record);

    int insertSelective(Hdfsinfo record);

    List<Hdfsinfo> selectByExample(HdfsinfoExample example);

    Hdfsinfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Hdfsinfo record, @Param("example") HdfsinfoExample example);

    int updateByExample(@Param("record") Hdfsinfo record, @Param("example") HdfsinfoExample example);

    int updateByPrimaryKeySelective(Hdfsinfo record);

    int updateByPrimaryKey(Hdfsinfo record);
}