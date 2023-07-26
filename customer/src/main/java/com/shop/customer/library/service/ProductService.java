package com.shop.customer.library.service;

import com.shop.customer.library.dto.ProductDto;
import com.shop.customer.library.model.Product;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    List<ProductDto> findAll();

    Product save(MultipartFile imageProduct, ProductDto productDto);

    Product update(MultipartFile imageProduct, ProductDto productDto);

    void deleteById(Long id);

    void enableById(Long id);

    ProductDto getById(Long id);

    Page<ProductDto> pageProducts(int pageNo);

    Page<ProductDto> searchProducts(int pageNo, String keyword);

    List<Product> getAllProducts();

    List<Product> listViewProducts();

    Product getProductById(Long id);

    List<Product> getRelatedProducts(Long categoryId);

    List<Product> getProductsInCategory(Long categoryId);

    List<Product> filterHighPrice();

    List<Product> filterLowPrice();

    List<Product> sort(Sort sort);
}
