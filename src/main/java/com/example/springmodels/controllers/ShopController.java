package com.example.springmodels.controllers;


import com.example.springmodels.repos.ApplicationRepository;
import com.example.springmodels.repos.CategoryRepository;
import com.example.springmodels.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER')")
public class ShopController {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ApplicationRepository applicationRepository;

    @Autowired
    public ShopController(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          ApplicationRepository applicationRepository){
        this.applicationRepository = applicationRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }


}
