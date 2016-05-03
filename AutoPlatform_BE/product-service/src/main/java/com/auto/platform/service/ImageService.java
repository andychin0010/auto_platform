package com.auto.platform.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chishingchin on 3/10/16.
 */
public interface ImageService {

    void createImageByProductId(long productId, MultipartFile file);
}
