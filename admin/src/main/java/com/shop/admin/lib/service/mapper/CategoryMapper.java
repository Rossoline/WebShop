package com.shop.admin.lib.service.mapper;

import com.shop.admin.lib.dto.CategoryDto;
import com.shop.admin.lib.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper implements RequestDtoMapper<CategoryDto, Category>,
        ResponseDtoMapper<CategoryDto, Category> {
    @Override
    public Category mapToModel(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setStatus(categoryDto.getActivationStatus());
        return category;
    }

    @Override
    public CategoryDto mapToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setActivationStatus(category.getStatus());
        return categoryDto;
    }
}
