package com.example.springmodels.controllers;

import com.example.springmodels.models.Category;
import com.example.springmodels.models.Product;
import com.example.springmodels.models.Status;
import com.example.springmodels.repos.CategoryRepository;
import com.example.springmodels.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER')")
public class ProductController {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/product")
    String index(Model model) {
        Iterable<Product> collection = productRepository.findAll();
        model.addAttribute("products", collection);
        return "productManager/product/index";
    }

    @GetMapping("/product/add")
    String add(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        model.addAttribute("product", new Product());
        return "productManager/product/add";
    }

    @GetMapping("/product/edit/{id}")
    String edit(Model model, @PathVariable("id") int id){
        Product product = productRepository.findById(id).orElseThrow();
        Iterable<Category> categories = categoryRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "productManager/product/update";
    }

    @PostMapping("/product/edit/{id}")
    String update(Model model, @PathVariable("id") int id,
                  @ModelAttribute("product") Product product,
                  @ModelAttribute("id_category") int id_category){
        Category category = categoryRepository.findById(id_category).orElseThrow();
        Product productToUpdate = productRepository.findById(id).orElseThrow();
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategory(category);
        productRepository.save(productToUpdate);
        return "redirect:/product/product";
    }

    @PostMapping("/product/add")
    String store(Model model, @Valid @ModelAttribute("product") Product product,
                 @RequestParam(name="id_category", required = false) int id_category){
        Category category = categoryRepository.findById(id_category).orElseThrow();
        product.setCategory(category);
        productRepository.save(product);
        return "redirect:/product/product";
    }

    @PostMapping("/product/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        productRepository.deleteById(id);
        return "redirect:/product/product";
    }
}
