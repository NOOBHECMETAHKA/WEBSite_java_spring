package com.example.springmodels.controllers;


import com.example.springmodels.models.Address;
import com.example.springmodels.models.ModelUser;
import com.example.springmodels.repos.AddressRepository;
import com.example.springmodels.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
@PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER')")
public class AddressController {
    AddressRepository addressRepository;

    UserRepository userRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository,
                             UserRepository userRepository){
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @GetMapping("/address")
    String index(Model model) {
        Iterable<Address> collection = addressRepository.findAll();
        model.addAttribute("addresses", collection);
        return "productManager/address/index";
    }

    @GetMapping("/address/add")
    String add(Model model){
        model.addAttribute("address", new Address());
        model.addAttribute("users", userRepository.findAll());
        return "productManager/address/add";
    }

    @PostMapping("/address/add")
    String store(Model model, @Valid @ModelAttribute("address") Address address,
                 @RequestParam(name="id_user", required = false) long user_id){
        ModelUser user = userRepository.findById(user_id).orElseThrow();
        address.setModelUser(user);
        addressRepository.save(address);
        return "redirect:/product/address";
    }

    @PostMapping("/address/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        addressRepository.deleteById(id);
        return "redirect:/product/address";
    }
}
