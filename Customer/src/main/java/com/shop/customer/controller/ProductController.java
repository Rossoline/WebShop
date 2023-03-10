package com.shop.customer.controller;

import com.shop.library.model.Category;
import com.shop.library.model.Product;
import com.shop.library.service.CategoryService;
import com.shop.library.service.ProductService;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService){
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public String products(Model model){
        List<Category> categoryList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getAllProducts();
        List<Product> listViewProducts = productService.listViewProducts();
        model.addAttribute("categories", categoryList);
        model.addAttribute("viewProducts", listViewProducts);
        model.addAttribute("products", products);
        model.addAttribute("size", products.size());
        return "shop";
    }

    @GetMapping("/find-product/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model){
        Product product = productService.getProductById(id);
        Long categoryId = product.getCategory().getId();
        List<Product> products = productService.getRelatedProducts(categoryId);
        model.addAttribute("product", product);
        model.addAttribute("products", products);
        return "product-detail";
    }

    @GetMapping("/products-in-category/{id}")
    public String getProductsInCategory(@PathVariable("id") Long categoryId, Model model){
        Category category = categoryService.findById(categoryId);
        List<Category> categories = categoryService.getCategoryAndProduct();
        List<Product> products = productService.getProductsInCategory(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("size", products.size());
        return "products-in-category";
    }

    @GetMapping("/sorted-high")
    public String highPrice(@RequestParam(defaultValue = "desc") String sort, Model model){
        sortByPrice(sort, model);
        return "filter-high-price";
    }

    @GetMapping("/sorted-low")
    public String lowPrice(@RequestParam(defaultValue = "asc") String sort, Model model){
        sortByPrice(sort, model);
        return "filter-low-price";
    }

    private void sortByPrice(@RequestParam(defaultValue = "asc") String sort,
                             Model model){
        Sort.Direction direction = sort.equalsIgnoreCase("asc")
                ? Sort.Direction.ASC : Sort.Direction.DESC;
        List<Category> categories = categoryService.findAllByActivated();
        List<Category> categoryList = categoryService.getCategoryAndProduct();
        List<Product> products = productService.sort(Sort.by(direction, "costPrice"));
        model.addAttribute("size", products.size());
        model.addAttribute("categoryDtoList", categoryList);
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
    }
}
