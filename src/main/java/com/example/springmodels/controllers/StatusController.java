package com.example.springmodels.controllers;

import com.example.springmodels.models.Status;
import com.example.springmodels.repos.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/product")
@PreAuthorize("hasAnyAuthority('PRODUCT_MANAGER')")
public class StatusController {

    @Autowired
    StatusRepository statusRepository;

    public StatusController(StatusRepository statusRepository){
        this.statusRepository = statusRepository;
    }

    @GetMapping("/status")
    String index(Model model) {
        Iterable<Status> statusList = statusRepository.findAll();
        model.addAttribute("statuses", statusList);
        return "productManager/status/index";
    }

    @GetMapping("/status/add")
    String add(Model model){
        model.addAttribute("status", new Status());
        return "productManager/status/add";
    }

    @PostMapping("/status/add")
    String store(Model model, @Valid @ModelAttribute("status") Status status){
        statusRepository.save(status);
        return "redirect:/product/status";
    }

    @GetMapping("/status/edit/{id}")
    String edit(Model model, @PathVariable("id") int id){
        Status status = statusRepository.findById(id).orElse(null);
        if(status == null) return "redirect:/product/status";
        model.addAttribute("status", status);
        return "productManager/status/update";
    }

    @PostMapping("/status/edit/{id}")
    String update(Model model, @PathVariable("id") int id,
                  @Valid @ModelAttribute("status") Status status){
        Status statusTOUPDATE = statusRepository.findById(id).orElse(null);
        if(statusTOUPDATE != null){
            statusTOUPDATE.setName(status.getName());
            statusRepository.save(statusTOUPDATE);
        }
        return "redirect:/product/status";
    }

    @PostMapping("/status/delete/{id}")
    String delete(Model model, @PathVariable("id") int id){
        statusRepository.deleteById(id);
        return "redirect:/product/status";
    }

}
