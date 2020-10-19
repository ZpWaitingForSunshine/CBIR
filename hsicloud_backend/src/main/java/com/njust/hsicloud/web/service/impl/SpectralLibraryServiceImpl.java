package com.njust.hsicloud.web.service.impl;

import com.njust.hsicloud.web.dao.SpecdataMapper;
import com.njust.hsicloud.web.dao.SpeclibnameMapper;
import com.njust.hsicloud.web.model.Specdata;
import com.njust.hsicloud.web.model.SpecdataExample;
import com.njust.hsicloud.web.model.SpecdataWithBLOBs;
import com.njust.hsicloud.web.model.Speclibname;
import com.njust.hsicloud.web.service.SpectralLibraryService;
import org.apache.avro.specific.SpecificData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 *
 * @author StarZou
 * @since 2014年7月5日 上午11:54:24
 */
@Service
public class SpectralLibraryServiceImpl implements SpectralLibraryService {
    @Resource
    private SpeclibnameMapper speclibnameMapper;

    @Resource
    private SpecdataMapper specdataMapper;

    @Override
    public List<Speclibname> selectAll() {
        return speclibnameMapper.selectAll();
    }

    @Override
    public int insert(Speclibname speclibname) {
        return 0;
    }

    @Override
    public int update(Speclibname speclibname) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public Speclibname selectById(Long id) {
        return null;
    }

    @Override
    public Speclibname selectOne() {
        return null;
    }

    @Override
    public List<Speclibname> selectList() {
        return null;
    }

    public List<Specdata> selectByExample(SpecdataExample example){
        return specdataMapper.selectByExample(example);
    }

    public List<SpecdataWithBLOBs> selectByExampleWithBLOBs(SpecdataExample example){
        return specdataMapper.selectByExampleWithBLOBs(example);
    }

}
