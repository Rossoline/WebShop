package com.shop.library.service.mapper;

import com.shop.library.dto.ProductDto;
import com.shop.library.model.Product;
import com.shop.library.model.enums.ActivationStatus;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper
        implements RequestDtoMapper<ProductDto, Product>, ResponseDtoMapper<ProductDto, Product> {

    private final CategoryMapper categoryMapper;

    public ProductMapper(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Product mapToModel(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCostPrice(productDto.getCostPrice());
        product.setCurrentQuantity(productDto.getCurrentQuantity());
        product.setCategory(categoryMapper.mapToModel(productDto.getCategoryDto()));
        product.setStatus(ActivationStatus.ACTIVATED);
        return product;
    }

    @Override
    public ProductDto mapToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCategoryDto(categoryMapper.mapToDto(product.getCategory()));
        productDto.setCostPrice(product.getCostPrice());
        productDto.setImage(product.getImage());
        productDto.setStatus(product.getStatus());
        return productDto;
    }
}
