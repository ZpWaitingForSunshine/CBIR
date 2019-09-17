package com.njust.cbir.web.service;

import com.njust.cbir.core.generic.GenericService;
import com.njust.cbir.web.model.Hdfsinfo;
import com.njust.cbir.web.model.Image;

import java.util.List;

/**
 * 用户 业务 接口
 *
 * @author Penn
 * @since 2019年7月5日 上午11:53:33
 **/
public interface HDFSInfoService extends GenericService<Hdfsinfo, Long> {
    int insert(Hdfsinfo hdfsinfo);
    Hdfsinfo get(int id);
}


