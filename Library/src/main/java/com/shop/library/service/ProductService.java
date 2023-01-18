package com.shop.library.service;

import com.shop.library.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();
    Product save(Product product);
    Product findById(Long id);
    Product update(Product product);

}
