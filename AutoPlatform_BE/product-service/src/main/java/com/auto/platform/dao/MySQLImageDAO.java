package com.auto.platform.dao;

import com.auto.platform.domain.Image;
import com.auto.platform.domain.ImageType;
import com.auto.platform.mapper.ImageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chishingchin on 3/10/16.
 */

@Repository
public class MySQLImageDAO implements ImageDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String CREATE_IMAGE = "INSERT INTO images (`product_id`, `path`) VALUES(?, ?)";
    private final String GET_IMAGES_BY_PRODUCT_ID = "SELECT * FROM images WHERE product_id = ?";

    @Override
    public void createImageByProductId(long productId, String path) {

        jdbcTemplate.update(CREATE_IMAGE, new Object[]{productId, path});
    }

    @Override
    public List<Image> getImagesByProductId(long productId) {

        List<Image> result = new ArrayList<>();

        try {
            result = jdbcTemplate.query(GET_IMAGES_BY_PRODUCT_ID, new Object[]{productId}, new ImageRowMapper());
        } catch (Exception e) {

        }
        return result;
    }
}
