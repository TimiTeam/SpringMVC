package com.gmail.timatiblackstar666.SpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("")
    public String adminHome(Model model){

        return "admin";
    }

    @GetMapping("/users")
    public String users(Model model){

        return "users";
    }
}
