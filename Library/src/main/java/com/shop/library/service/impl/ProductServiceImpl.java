package com.shop.library.service.impl;

import com.shop.library.model.Product;
import com.shop.library.repository.ProductRepository;
import com.shop.library.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Product update(Product product) {
        return repository.save(product);
    }
}
