package com.gmail.timatiblackstar666.SpringMVC.controller;

import com.gmail.timatiblackstar666.SpringMVC.models.User;
import com.gmail.timatiblackstar666.SpringMVC.services.IUserService;
import com.gmail.timatiblackstar666.SpringMVC.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class GreetingController {

    @Autowired
    private IUserService userService;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World")
                                       String name, Model model){
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping("/registration")
    public String registerPage(Model model){
        model.addAttribute("newUser", new User());
        return "register";
    }

    @PostMapping("/registration")
    public String registerDone(@ModelAttribute("newUser") User user, Model model){
        if (user.getRole() == null){
            user.setRole(Constants.ROLE_USER);
        }
        user.setEnable(true);
        userService.saveUser(user);
        //model.addAttribute("name", user.getName());
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/home")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            User u = userService.findUserByLogin(auth.getName());
            if (u != null){
                model.addAttribute("name", u.getName());
                if (u.getRole().equals(Constants.ROLE_ADMIN)){
                    return "redirect:/admin";
                }
            }
        }
        return "home";
    }

    /*
    @PostMapping("/login")
    public String loginDone(@ModelAttribute("user") User user, Model model){
        model.addAttribute("name", user.getName());
        //TODO if logged in user is admin then return an adminGreeting page otherwise return greeting
        //if (user.getRoles().containse(E_ADMIN)
        //return adminGreeting
        return "greeting";
    }*/
}