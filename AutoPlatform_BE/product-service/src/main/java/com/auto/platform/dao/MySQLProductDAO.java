package com.auto.platform.dao;

import com.auto.platform.domain.Product;
import com.auto.platform.mapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by chishingchin on 3/3/16.
 */

@Repository
public class MySQLProductDAO implements ProductDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final int OFFSET = 0;
    private final int LIMIT = 10;

    private final String GET_PRODUCTS = "SELECT * FROM products ORDER BY id DESC LIMIT ? OFFSET ?";
    private final String GET_ACTIVE_PRODUCTS = "SELECT * FROM products WHERE state = 'LIVE' ORDER BY id DESC LIMIT ? OFFSET ?";
    private final String GET_PRODUCT_BY_ID = "SELECT * FROM products WHERE id = ?";
    private final String GET_PRODUCT_COUNT = "SELECT COUNT(*) FROM products";
    private final String CREATE_PRODUCT = "INSERT INTO products (`make`, `model`, `mileage`, `color`, `description`) VALUES(?, ?, ?, ?, ?)";
    private final String UPDATE_PRODUCT = "UPDATE products SET `make` = ?, `model` = ?, `mileage` = ?, `color` = ?, `description` = ? WHERE id = ?";

    @Override
    public List<Product> getProducts(int offset, int limit, boolean all) {

        return jdbcTemplate.query(all ? GET_PRODUCTS : GET_ACTIVE_PRODUCTS, new Object[]{limit == 0 ? LIMIT : limit, offset == 0 ? OFFSET : offset}, new ProductRowMapper());
    }

    @Override
    public Product getProductById(long id) {
        Product result;

        try {
            result = jdbcTemplate.queryForObject(GET_PRODUCT_BY_ID, new Object[]{id}, new ProductRowMapper());
        } catch (EmptyResultDataAccessException e) {
            result = null;
        }

        return result;
    }

    @Override
    public Integer getProductCount() {
        Integer result = 0;

        try {
            result = jdbcTemplate.queryForObject(GET_PRODUCT_COUNT, new Object[]{}, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            result = 0;
        }

        return result;
    }

    @Override
    public Product createProduct(Product product) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(CREATE_PRODUCT, new String[] {"id"});
                    ps.setString(1, product.getMake());
                    ps.setString(2, product.getModel());
                    ps.setInt(3, product.getMileage());
                    ps.setString(4, product.getColor());
                    ps.setString(5, product.getDescription());
                    return ps;
                }, keyHolder);

        product.setId(keyHolder.getKey().longValue());

        return product;
    }

    @Override
    public Product updateProduct(Product product) {
                KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCT);
                    ps.setString(1, product.getMake());
                    ps.setString(2, product.getModel());
                    ps.setInt(3, product.getMileage());
                    ps.setString(4, product.getColor());
                    ps.setString(5, product.getDescription());
                    ps.setLong(6, product.getId());
                    return ps;
                }
        );

        return getProductById(product.getId());
    }
}
