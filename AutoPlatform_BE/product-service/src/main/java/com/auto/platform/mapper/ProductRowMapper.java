package com.auto.platform.mapper;

import com.auto.platform.domain.Product;
import com.auto.platform.domain.ProductState;
import com.auto.platform.domain.ProductStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chishingchin on 3/3/16.
 */
public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setState(ProductState.valueOf(resultSet.getString("state")));
        product.setMake(resultSet.getString("make"));
        product.setModel(resultSet.getString("model"));
        product.setStatus(ProductStatus.valueOf(resultSet.getString("status")));
        product.setYear(resultSet.getInt("year"));
        product.setMileage(resultSet.getInt("mileage"));
        product.setColor(resultSet.getString("color"));
        product.setDescription(resultSet.getString("description"));
        product.setCreationTimestamp(resultSet.getLong("creation_timestamp"));
        product.setModificationTimestamp(resultSet.getLong("modification_timestamp"));
        return product;
    }
}
