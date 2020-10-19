package com.njust.hsicloud.web.dao;

import com.njust.hsicloud.web.model.Image;
import com.njust.hsicloud.web.model.ImageExample;
import com.njust.hsicloud.web.model.ImageWithBLOBs;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ImageMapper {
    int countByExample(ImageExample example);

    int deleteByExample(ImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ImageWithBLOBs record);

    int insertSelective(ImageWithBLOBs record);

    List<ImageWithBLOBs> selectByExampleWithBLOBs(ImageExample example);

    List<Image> selectByExample(ImageExample example);

    ImageWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ImageWithBLOBs record, @Param("example") ImageExample example);

    int updateByExampleWithBLOBs(@Param("record") ImageWithBLOBs record, @Param("example") ImageExample example);

    int updateByExample(@Param("record") Image record, @Param("example") ImageExample example);

    int updateByPrimaryKeySelective(ImageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ImageWithBLOBs record);

    int updateByPrimaryKey(Image record);

    List<Image> selectTopN(int n);

//    int insertTemp(Image record);
    // page
    List<Image> selectByOffset(@Param("n") int n, @Param("offset") int offset);

    int getSum();



}