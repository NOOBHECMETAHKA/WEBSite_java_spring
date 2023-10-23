package com.example.springmodels.controllers;

import com.example.springmodels.models.Address;
import com.example.springmodels.models.Category;
import com.example.springmodels.models.ModelUser;
import com.example.springmodels.repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER')")
public class CategoryController {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/category")
    String index(Model model) {
        Iterable<Category> collection = categoryRepository.findAll();
        model.addAttribute("categories", collection);
        return "productManager/category/index";
    }

    @GetMapping("/category/add")
    String add(Model model){
        model.addAttribute("category", new Category());
        model.addAttribute("users", categoryRepository.findAll());
        return "productManager/category/add";
    }

    @PostMapping("/category/add")
    String store(Model model, @Valid @ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return "redirect:/product/category";
    }

    @PostMapping("/category/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        categoryRepository.deleteById(id);
        return "redirect:/product/category";
    }

}
