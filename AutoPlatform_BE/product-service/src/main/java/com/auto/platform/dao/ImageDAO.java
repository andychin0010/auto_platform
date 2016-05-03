package com.auto.platform.dao;

import com.auto.platform.domain.Image;

import java.util.List;

/**
 * Created by chishingchin on 3/10/16.
 */
public interface ImageDAO {

    void createImageByProductId(long productId, String path);

    List<Image> getImagesByProductId(long productId);

}
