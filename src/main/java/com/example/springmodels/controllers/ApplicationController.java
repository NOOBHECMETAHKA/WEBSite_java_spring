package com.example.springmodels.controllers;

import com.example.springmodels.models.Application;
import com.example.springmodels.repos.ApplicationRepository;
import com.example.springmodels.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class ApplicationController {
    ApplicationRepository applicationRepository;
    UserRepository userRepository;
    @Autowired
    public ApplicationController(ApplicationRepository applicationRepository, UserRepository userRepository){
        this.applicationRepository = applicationRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/application")
    String index(Model model){
        Iterable<Application> applications = applicationRepository.findAll();
        model.addAttribute("applications", applications);
        return "admin/application/index";
    }

    @PostMapping(" /application/delete/{id}")
    String delete(Model model, @PathVariable(name="id", required = false) int id_application){
        applicationRepository.deleteById(id_application);
        return "redirect:/admin/application";
    }
}
