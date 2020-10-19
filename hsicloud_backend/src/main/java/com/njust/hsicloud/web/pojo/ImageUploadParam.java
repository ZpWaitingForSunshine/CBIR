package com.njust.hsicloud.web.pojo;

import com.njust.hsicloud.web.model.Image;
import org.springframework.web.multipart.MultipartFile;

public class ImageUploadParam extends Image {
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
