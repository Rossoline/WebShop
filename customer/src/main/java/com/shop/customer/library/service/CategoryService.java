package com.shop.customer.library.service;

import com.shop.customer.library.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category save(Category category);

    Category findById(Long id);

    Category update(Category category);

    void deleteById(Long id);

    void enableById(Long id);

    List<Category> findAllByActivated();

    List<Category> getCategoryAndProduct();
}
