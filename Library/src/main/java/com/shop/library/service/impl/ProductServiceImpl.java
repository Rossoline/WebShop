package com.shop.library.service.impl;

import com.shop.library.dto.ProductDto;
import com.shop.library.model.Product;
import com.shop.library.model.enums.ActivationStatus;
import com.shop.library.repository.ProductRepository;
import com.shop.library.service.ProductService;
import com.shop.library.service.mapper.ProductMapper;
import com.shop.library.utils.ImageUpload;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductServiceImpl implements ProductService {
    private static final int MAX_PAGES = 5;
    private final ProductRepository repository;
    private final ImageUpload imageUpload;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository repository, ImageUpload imageUpload, ProductMapper productMapper){
        this.repository = repository;
        this.imageUpload = imageUpload;
        this.productMapper = productMapper;
    }

    @Override
    public List<ProductDto> findAll(){
        List<Product> products = repository.findAll();
        return toDtoList(products);
    }

    @Override
    public Product save(MultipartFile imageProduct, ProductDto productDto){
        try{
            Product product = productMapper.mapToModel(productDto);
            if(imageProduct == null){
                product.setImage(null);
            }else{
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            return repository.save(product);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(MultipartFile imageProduct, ProductDto productDto){
        try{
            Product product = productMapper.mapToModel(productDto);
            if(imageProduct == null){
                product.setImage(product.getImage());
            }else{
                if(!imageUpload.checkExisted(imageProduct)){
                    imageUpload.uploadImage(imageProduct);
                }
                product.setImage(Base64.getEncoder().encodeToString(imageProduct.getBytes()));
            }
            return repository.save(product);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id){
        Product product = repository.getById(id);
        product.setStatus(ActivationStatus.DELETED);
        repository.save(product);
    }

    @Override
    public void enableById(Long id){
        Product product = repository.getById(id);
        product.setStatus(ActivationStatus.ACTIVATED);
        repository.save(product);
    }

    @Override
    public ProductDto getById(Long id){
        Product product = repository.getById(id);
        return productMapper.mapToDto(product);
    }

    @Override
    public Page<ProductDto> pageProducts(int pageNo){
        Pageable pageable = PageRequest.of(pageNo, MAX_PAGES);
        List<ProductDto> products = toDtoList(repository.findAll());
        return toPage(products, pageable);
    }

    @Override
    public Page<ProductDto> searchProducts(int pageNo, String keyword){
        Pageable pageable = PageRequest.of(pageNo, MAX_PAGES);
        List<ProductDto> productDtoList = toDtoList(repository.searchProductsList(keyword));
        return toPage(productDtoList, pageable);
    }

    private Page<ProductDto> toPage(List<ProductDto> list, Pageable pageable){
        if(pageable.getOffset() >= list.size()){
            return Page.empty();
        }
        int startIndex = (int) pageable.getOffset();
        int endIndex = ((pageable.getOffset() + pageable.getPageSize()) > list.size())
                ? list.size()
                : (int) (pageable.getOffset() + pageable.getPageSize());
        List<ProductDto> subList = list.subList(startIndex, endIndex);
        return new PageImpl<>(subList, pageable, list.size());
    }

    private List<ProductDto> toDtoList(List<Product> products){
        List<ProductDto> productDtoList = new ArrayList<>();
        for(Product product : products){
            productDtoList.add(productMapper.mapToDto(product));
        }
        return productDtoList;
    }

    @Override
    public List<Product> getAllProducts(){
        return repository.getAllProducts();
    }

    @Override
    public List<Product> listViewProducts(){
        return repository.listViewProducts();
    }

    @Override
    public Product getProductById(Long id){
        return repository.getById(id);
    }

    @Override
    public List<Product> getRelatedProducts(Long categoryId){
        return repository.getRelatedProducts(categoryId);
    }

    @Override
    public List<Product> getProductsInCategory(Long categoryId){
        return repository.getProductsInCategory(categoryId);
    }

    @Override
    public List<Product> filterHighPrice(){
        return repository.filterHighPrice();
    }

    @Override
    public List<Product> filterLowPrice(){
        return repository.filterLowerPrice();
    }

    @Override public List<Product> sort(Sort sort){
        return repository.findAll(sort);
    }
}
