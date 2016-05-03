package com.auto.platform.controller;

import com.auto.platform.domain.Product;
import com.auto.platform.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chishingchin on 3/2/16.
 */

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @CrossOrigin
    @RequestMapping(value = "", method = RequestMethod.GET)
    ResponseEntity<?> getProducts(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                                  @RequestParam(value = "limit", required = false, defaultValue = "10") int limit,
                                  @RequestParam(value = "all", defaultValue = "false") boolean all) {

        return new ResponseEntity<>(productService.getProducts(offset, limit, all), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getProduct(@PathVariable long id) {

        Product product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Product Not Found.", HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin
    @RequestMapping(value = "/count", method = RequestMethod.GET)
    ResponseEntity<?> getProductCount() {
        return new ResponseEntity<>(productService.getProductCount(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
}
