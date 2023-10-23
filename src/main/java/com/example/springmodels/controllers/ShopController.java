package com.example.springmodels.controllers;


import com.example.springmodels.dao.ProductDao;
import com.example.springmodels.models.*;
import com.example.springmodels.repos.*;
import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('USER')")
public class ShopController {
    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    ApplicationRepository applicationRepository;
    UserRepository userRepository;
    AddressRepository addressRepository;
    StatusRepository statusRepository;
    OrderRepository orderRepository;

    ProductDao productDao;
    @Autowired
    public ShopController(ProductRepository productRepository,
                          CategoryRepository categoryRepository,
                          ApplicationRepository applicationRepository,
                          UserRepository userRepository,
                          AddressRepository addressRepository,
                          StatusRepository statusRepository,
                          OrderRepository orderRepository,
                          ProductDao productDao){
        this.applicationRepository = applicationRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.productDao = productDao;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.addressRepository = addressRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/shop")
    String index(Model model){
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("countProduct", productDao.count());
        return "user/product/index";
    }

    @GetMapping("/shop/basket/")
    String products(Model model){
        model.addAttribute("basket", productDao.index());
        Iterable<Address> addresses = addressRepository.findAll();
        model.addAttribute("addresses", addresses);
        return "user/other/basket";
    }
    @PostMapping("shop/basket/clear")
    String basketClear(){
        productDao.clear();
        return "redirect:/shop";
    }
    @PostMapping("/shop/basket/{id}")
    String basket(Model model, @PathVariable(name = "id", required = true) int id_product){
        productRepository.findById(id_product).ifPresent(product -> productDao.insert(product));
        return "redirect:/shop";
    }

    @GetMapping("/application/create")
    String ApplicationCreator(Model model){
        Iterable<Application> applications = applicationRepository.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userEmail", auth.getName());
        model.addAttribute("applications", applications);
        model.addAttribute("application", new Application());
        return "user/other/application";
    }

    @PostMapping("/application/create")
    String storeApplication(Model model, @ModelAttribute("application") Application application){
        ModelUser user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        application.setLocalDate(LocalDate.now());
        application.setPerson(user);
        applicationRepository.save(application);
        return "redirect:/application/create";
    }

    @PostMapping("/order/create")
    String makeOrder(Model model, @ModelAttribute(name = "id_address") int id_address){
        Address address = addressRepository.findById(id_address).orElseThrow();
        Status status = statusRepository.findById(1).orElse(new Status("В разработке"));
        Order order = new Order(LocalDate.now(), status, address);
        orderRepository.save(order);
        return "redirect:/shop";
    }


}

