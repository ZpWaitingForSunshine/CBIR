package com.njust.cbir.web.service;

import com.njust.cbir.core.generic.GenericService;
import com.njust.cbir.web.model.Envi;
import com.njust.cbir.web.model.Role;

import java.util.List;

public interface ImageService extends GenericService<Role, Long> {
    /**
     *
     */

    List<Envi> getAll();
    int insertEnvi(Envi record);
    Envi getByFilename(String filename);
}
