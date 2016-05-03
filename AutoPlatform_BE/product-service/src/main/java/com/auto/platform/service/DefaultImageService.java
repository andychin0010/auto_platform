package com.auto.platform.service;

import com.auto.platform.dao.ImageDAO;
import com.auto.platform.domain.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * Created by chishingchin on 3/10/16.
 */

@Service
public class DefaultImageService implements ImageService {

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public void createImageByProductId(long productId, MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        System.out.println(extension + "!!!!");
        if (!file.isEmpty()) {
            try {
//                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("/Users/chishingchin/Documents/AutoPlatform_Onsen/images/" + fileName + extension)));
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("/home/ubuntu/AutoPlatform_Onsen/images/" + fileName + extension)));
                FileCopyUtils.copy(file.getInputStream(), stream);
                stream.close();

                imageDAO.createImageByProductId(productId, "images/" + fileName + extension);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}
