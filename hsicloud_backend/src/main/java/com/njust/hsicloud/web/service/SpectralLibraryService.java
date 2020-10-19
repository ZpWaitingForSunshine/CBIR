package com.njust.hsicloud.web.service;



import com.njust.hsicloud.core.generic.GenericService;
import com.njust.hsicloud.web.model.Specdata;
import com.njust.hsicloud.web.model.SpecdataExample;
import com.njust.hsicloud.web.model.SpecdataWithBLOBs;
import com.njust.hsicloud.web.model.Speclibname;

import java.util.List;

public interface SpectralLibraryService extends GenericService<Speclibname, Long> {

    List<Speclibname> selectAll(); // 列出所有的spectral名字

    // 根据spectral lib name 的id去获取 该类所有的spectral name
    List<Specdata> selectByExample(SpecdataExample example);

    // 查找spectral
    List<SpecdataWithBLOBs> selectByExampleWithBLOBs(SpecdataExample example);
}
