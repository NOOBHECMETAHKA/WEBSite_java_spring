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

    @GetMapping("/address/edit/{id}")
    String edit(Model model, @PathVariable("id") int id){
        Address address = addressRepository.findById(id).orElseThrow();
        Iterable<ModelUser> users = userRepository.findAll();
        model.addAttribute("address", address);
        model.addAttribute("users", users);
        return "productManager/address/update";
    }

    @PostMapping("address/edit/{id}")
    String update(Model model, @PathVariable("id") int id,
                  @ModelAttribute("address") Address address,
                  @ModelAttribute("id_user") long id_user){
        Address addressToUpdate = addressRepository.findById(id).orElseThrow();
        ModelUser modelUser = userRepository.findById(id_user).orElseThrow();
        addressToUpdate.setCity(address.getCity());
        addressToUpdate.setStreet(address.getStreet());
        addressToUpdate.setHouse(address.getHouse());
        addressToUpdate.setEntrance(address.getEntrance());
        addressToUpdate.setApartment(address.getApartment());
        addressToUpdate.setModelUser(modelUser);
        addressRepository.save(addressToUpdate);
        return "redirect:/product/address";
    }

    @PostMapping("/address/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        addressRepository.deleteById(id);
        return "redirect:/product/address";
    }
}
