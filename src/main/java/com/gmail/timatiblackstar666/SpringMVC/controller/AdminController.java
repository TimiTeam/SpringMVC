package com.gmail.timatiblackstar666.SpringMVC.controller;

import com.gmail.timatiblackstar666.SpringMVC.exceptions.UserNotFoundException;
import com.gmail.timatiblackstar666.SpringMVC.models.User;
import com.gmail.timatiblackstar666.SpringMVC.services.IUserService;
import com.gmail.timatiblackstar666.SpringMVC.utils.Constants;
import com.gmail.timatiblackstar666.SpringMVC.utils.UserUtils;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private IUserService userService;

    @GetMapping("")
    public String adminHome(Model model){
        User user = userUtils.getCurrentUser();
        if (user != null){
            model.addAttribute("name", user.getName());
            model.addAttribute("role", user.getRole());
        }
        return "admin";
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "users";
    }

    @GetMapping("/users/editUser")
    public String editUser(@RequestParam(name = "id", required = true)String id, Model model){
        User user = null;
        try {
            user = userService.findUserById(id);
            model.addAttribute("user", user);
            model.addAttribute("roles", Constants.ALL_ROLES);
        } catch (UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
            e.printStackTrace();
        }
        return "editUser";
    }

    @PostMapping("users/editUser")
    public String editUserSave(@ModelAttribute("user") User user, Model model){
        if (user != null){
            userService.saveUser(user);
        }
        return "redirect:/admin/users";
    }
}
