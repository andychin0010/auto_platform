package com.auto.platform.service;

import com.auto.platform.dao.ImageDAO;
import com.auto.platform.dao.ProductDAO;
import com.auto.platform.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chishingchin on 3/3/16.
 */

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ImageDAO imageDao;

    @Override
    public List<Product> getProducts(int offset, int limit, boolean all) {

        List<Product> products = productDAO.getProducts(offset, limit, all);
        for (Product product : products) {
            product.setImages(imageDao.getImagesByProductId(product.getId()));
        }

        return products;
    }

    @Override
    public Product getProductById(long id) {

        Product product = productDAO.getProductById(id);
        if (product != null) {
            product.setImages(imageDao.getImagesByProductId(id));
        }

        return product;
    }

    @Override
    public Integer getProductCount() {
        return productDAO.getProductCount();
    }

    @Override
    public Product createProduct(Product product) {
        return productDAO.createProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDAO.updateProduct(product);
    }
}
