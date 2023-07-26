package com.shop.admin.lib.service.impl;

import com.shop.admin.lib.model.Category;
import com.shop.admin.lib.model.enums.ActivationStatus;
import com.shop.admin.lib.repository.CategoryRepository;
import com.shop.admin.lib.service.CategoryService;
import com.shop.admin.lib.service.mapper.CategoryMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }

    @Override
    public Category save(Category category) {
        Category categorySave = new Category(category.getName());
        return repository.save(categorySave);
    }

    @Override
    public Category findById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Category update(Category category) {
        Category categoryUpdate;
        try {
            categoryUpdate = repository.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setStatus(category.getStatus());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return repository.save(categoryUpdate);
    }

    @Override
    public void deleteById(Long id) {
        Category category = repository.getById(id);
        category.setStatus(ActivationStatus.DELETED);
        repository.save(category);
    }

    @Override
    public void enableById(Long id) {
        Category category = repository.getById(id);
        category.setStatus(ActivationStatus.ACTIVATED);
        repository.save(category);
    }

    @Override
    public List<Category> findAllByActivated() {
        return repository.findAllByActivated();
    }

    @Override
    public List<Category> getCategoryAndProduct() {
        List<Category> categories = new ArrayList<>();
        repository.getCategoryAndProduct()
                .forEach(categoryDto -> categories.add(categoryMapper.mapToModel(categoryDto)));
        return categories;
    }
}
