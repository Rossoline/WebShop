package com.shop.admin.controller;

import com.shop.library.model.Category;
import com.shop.library.service.CategoryService;
import java.security.Principal;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String categories(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("size", categories.size());
        model.addAttribute("title", "Category");
        model.addAttribute("categoryNew", new Category());
        return "categories";
    }

    @PostMapping("/add-category")
    public String add(@ModelAttribute("categoryNew") Category category,
                      RedirectAttributes attributes){
        try{
            categoryService.save(category);
            attributes.addFlashAttribute("success", "Added successfully");
        }catch(DataIntegrityViolationException e){
            attributes.addFlashAttribute("failed",
                    "Failed to add, because duplicate name");
            throw new DataIntegrityViolationException("Failed to add, because duplicate name!" + e);
        }catch(Exception e){
            attributes.addFlashAttribute("failed", "Error server");
            throw new RuntimeException(e);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/findById",
            method = {RequestMethod.PUT, RequestMethod.GET})
    @ResponseBody
    public Category findById(Long id){
        return categoryService.findById(id);
    }

    @GetMapping("/update-category")
    public String update(Category category, RedirectAttributes attributes){
        try{
            category.setStatus(categoryService.findById(category.getId()).getStatus());
            categoryService.update(category);
            attributes.addFlashAttribute("success",
                    "Update successfully!");
        }catch(DataIntegrityViolationException e){
            attributes.addFlashAttribute("failed",
                    "Failed to update, because duplicate name!");
            throw new DataIntegrityViolationException("Failed to update, because duplicate name!" + e);
        }catch(Exception e){
            attributes.addFlashAttribute("failed", "Server error!");
            throw new RuntimeException(e);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/delete-category",
            method = {RequestMethod.PUT, RequestMethod.GET})
    public String delete(Long id, RedirectAttributes attributes){
        try{
            categoryService.deleteById(id);
            attributes.addFlashAttribute("success",
                    "Deleted successfully!");
        }catch(Exception e){
            attributes.addFlashAttribute("fail",
                    "Failed to update!");
            throw new RuntimeException(e);
        }
        return "redirect:/categories";
    }

    @RequestMapping(value = "/enable-category", method = {RequestMethod.PUT, RequestMethod.GET})
    public String enabledById(Long id, RedirectAttributes attributes){
        try{
            categoryService.enableById(id);
            attributes.addFlashAttribute("success",
                    "Enabled successfully!");
        }catch(Exception e){
            attributes.addFlashAttribute("fail",
                    "Failed to enable!");
            throw new RuntimeException(e);
        }
        return "redirect:/categories";
    }
}
