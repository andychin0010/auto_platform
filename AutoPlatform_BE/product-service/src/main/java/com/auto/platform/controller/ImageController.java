package com.auto.platform.controller;

import com.auto.platform.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by chishingchin on 3/5/16.
 */

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @CrossOrigin
    @RequestMapping(value = "/products/{product_id}/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadImage(@PathVariable(value = "product_id") long productId,
                                         @RequestParam(value = "file", required = true) MultipartFile file) {

        imageService.createImageByProductId(productId, file);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
