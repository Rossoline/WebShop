package com.shop.library.service;

import com.shop.library.dto.ProductDto;
import com.shop.library.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductDto> findAll();
    Product save(MultipartFile imageProduct, ProductDto productDto);
//    Product findById(Long id);
    Product update(ProductDto productDto);
    Product deleteById(Long id);
    Product enableById(Long id);

}
