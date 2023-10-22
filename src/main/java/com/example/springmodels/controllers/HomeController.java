package com.example.springmodels.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.http.HttpRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    String index(){
        return "index";
    }
}
