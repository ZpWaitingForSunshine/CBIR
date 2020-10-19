package com.njust.hsicloud.web.dao;

import com.njust.hsicloud.web.model.Photos;
import com.njust.hsicloud.web.model.PhotosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhotosMapper {
    int countByExample(PhotosExample example);

    int deleteByExample(PhotosExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Photos record);

    int insertSelective(Photos record);

    List<Photos> selectByExampleWithBLOBs(PhotosExample example);

    List<Photos> selectByExample(PhotosExample example);

    Photos selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Photos record, @Param("example") PhotosExample example);

    int updateByExampleWithBLOBs(@Param("record") Photos record, @Param("example") PhotosExample example);

    int updateByExample(@Param("record") Photos record, @Param("example") PhotosExample example);

    int updateByPrimaryKeySelective(Photos record);

    int updateByPrimaryKeyWithBLOBs(Photos record);

    int updateByPrimaryKey(Photos record);
}