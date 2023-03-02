package com.shop.library.service.impl;

import com.shop.library.dto.ProductDto;
import com.shop.library.model.Activation;
import com.shop.library.model.Product;
import com.shop.library.repository.ProductRepository;
import com.shop.library.service.ProductService;
import com.shop.library.utils.ImageUpload;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ImageUpload imageUpload;

    public ProductServiceImpl(ProductRepository repository, ImageUpload imageUpload) {
        this.repository = repository;
        this.imageUpload = imageUpload;
    }

    @Override
    public List<ProductDto> findAll() {
        List<Product> products = repository.findAll();
        return toDtoList(products);
    }

    @Override
    public Product save(MultipartFile imageProduct, ProductDto productDto) {
        try {
            Product product = new Product();
            if (imageProduct == null) {
                product.setImage(null);
            } else {
                if (imageUpload.uploadImage(imageProduct)) {
                    System.out.println("Upload successfully");
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setCategory(productDto.getCategory());
            product.setCostPrice(productDto.getCostPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setStatus(Activation.ACTIVATED);
            return repository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Product update(MultipartFile imageProduct, ProductDto productDto) {
        try {
            Product product = repository.getById(productDto.getId());
            if (imageProduct == null) {
                product.setImage(product.getImage());
            } else {
                if (!imageUpload.checkExisted(imageProduct)) {
                    imageUpload.uploadImage(imageProduct);
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            product.setName(productDto.getName());
            product.setDescription(productDto.getDescription());
            product.setSalePrice(productDto.getSalePrice());
            product.setCostPrice(productDto.getCostPrice());
            product.setCurrentQuantity(productDto.getCurrentQuantity());
            product.setCategory(productDto.getCategory());
            product.setStatus(productDto.getStatus());
            return repository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        Product product = repository.getById(id);
        product.setStatus(Activation.DELETED);
        repository.save(product);
    }

    @Override
    public void enableById(Long id) {
        Product product = repository.getById(id);
        product.setStatus(Activation.ACTIVATED);
        repository.save(product);
    }

    @Override
    public ProductDto getById(Long id) {
        Product product = repository.getById(id);
        return this.toDto(product);
    }

    @Override
    public Page<ProductDto> pageProducts(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<ProductDto> products = toDtoList(repository.findAll());
        return (Page<ProductDto>) toPage(products, pageable);
    }

    @Override
    public Page<ProductDto> searchProducts(int pageNo, String keyword) {
        Pageable pageable = PageRequest.of(pageNo, 5);
        List<ProductDto> productDtoList = toDtoList(repository.searchProductsList(keyword));
        return (Page<ProductDto>) toPage(productDtoList, pageable);
    }

    private Page toPage(List<ProductDto> list, Pageable pageable) {
        //TODO Whats mean all this?
        if (pageable.getOffset() >= list.size()) {
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List subList = list.subList(startIndex, endIndex);
        return new PageImpl(subList, pageable, list.size());
    }

    private List<ProductDto> toDtoList(List<Product> products) {
        List<ProductDto> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(this.toDto(product));
        }
        return productDtoList;
    }

    private ProductDto toDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrentQuantity(product.getCurrentQuantity());
        productDto.setCategory(product.getCategory());
        productDto.setSalePrice(product.getSalePrice());
        productDto.setCostPrice(product.getCostPrice());
        productDto.setImage(product.getImage());
        productDto.setStatus(product.getStatus());
        return productDto;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.getAllProducts();
    }

    @Override
    public List<Product> listViewProducts() {
        return repository.listViewProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Product> getRelatedProducts(Long categoryId) {
        return repository.getRelatedProducts(categoryId);
    }

    @Override
    public List<Product> getProductsInCategory(Long categoryId) {
        return repository.getProductsInCategory(categoryId);
    }

    @Override
    public List<Product> filterHighPrice() {
        return repository.filterHighPrice();
    }

    @Override
    public List<Product> filterLowPrice() {
        return repository.filterLowerPrice();
    }
}
