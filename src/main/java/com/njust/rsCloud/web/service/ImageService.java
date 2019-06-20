package com.njust.rsCloud.web.service;

import com.njust.rsCloud.core.generic.GenericService;
import com.njust.rsCloud.web.model.Envi;
import com.njust.rsCloud.web.model.Role;

import java.util.List;

public interface ImageService extends GenericService<Role, Long> {
    /**
     *
     */

    List<Envi> getAll();
    int insertEnvi(Envi record);
    Envi getByFilename(String filename);
}
