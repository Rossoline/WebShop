package com.shop.library.service.impl;

import com.shop.library.dto.CategoryDto;
import com.shop.library.model.Activation;
import com.shop.library.model.Category;
import com.shop.library.repository.CategoryRepository;
import com.shop.library.service.CategoryService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Category> findAll(){
        return repository.findAll();
    }

    @Override
    public Category save(Category category){
        Category categorySave = new Category(category.getName());
        return repository.save(categorySave);
    }

    @Override
    public Category findById(Long id){
        return repository.findById(id).get();
    }

    @Override
    public Category update(Category category){
        Category categoryUpdate = null;
        try{
            categoryUpdate = repository.findById(category.getId()).get();
            categoryUpdate.setName(category.getName());
            categoryUpdate.setStatus(category.getStatus());
        }catch(Exception e){
            //TODO change e.printst
            e.printStackTrace();
        }
        return repository.save(categoryUpdate);
    }

    @Override
    public void deleteById(Long id){
        Category category = repository.getById(id);
        category.setStatus(Activation.DELETED);
        repository.save(category);
    }

    @Override
    public void enableById(Long id){
        Category category = repository.getById(id);
        category.setStatus(Activation.ACTIVATED);
        repository.save(category);
    }

    @Override
    public List<Category> findAllByActivated(){
        return repository.findAllByActivated();
    }

    @Override
    public List<CategoryDto> getCategoryAndProduct(){
        return repository.getCategoryAndProduct();
    }
}
