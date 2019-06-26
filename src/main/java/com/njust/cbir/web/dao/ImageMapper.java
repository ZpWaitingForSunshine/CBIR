package com.njust.cbir.web.dao;

import com.njust.cbir.web.model.Image;
import com.njust.cbir.web.model.ImageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImageMapper {
    int countByExample(ImageExample example);

    int deleteByExample(ImageExample example);

    int deleteByPrimaryKey(Integer imageid);

    int insert(Image record);

    int insertSelective(Image record);

    List<Image> selectByExampleWithBLOBs(ImageExample example);

    List<Image> selectByExample(ImageExample example);

    Image selectByPrimaryKey(Integer imageid);

    int updateByExampleSelective(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByExampleWithBLOBs(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByPrimaryKeySelective(Image record);

    int updateByPrimaryKeyWithBLOBs(Image record);

    int updateByPrimaryKey(Image record);

    List<Image> selectTopN(int n);
}