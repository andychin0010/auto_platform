package com.auto.platform.dao;

import com.auto.platform.domain.Product;

import java.util.List;

/**
 * Created by chishingchin on 3/3/16.
 */
public interface ProductDAO {

    List<Product> getProducts(int offset, int limit, boolean all);

    Product getProductById(long id);

    Integer getProductCount();

    Product createProduct(Product product);

    Product updateProduct(Product product);
}
