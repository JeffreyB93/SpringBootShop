package com.example.springbootshop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String homePage(Model model) {
        model.addAttribute("message", "Hello");
        return "index";
    }

    @GetMapping("/goToUserPage")
    public String goToUserPage(Model model) {

        return "users/userPage";
    }
}
