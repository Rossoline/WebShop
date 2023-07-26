package com.shop.admin.lib.service;

import com.shop.admin.lib.model.Category;
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
