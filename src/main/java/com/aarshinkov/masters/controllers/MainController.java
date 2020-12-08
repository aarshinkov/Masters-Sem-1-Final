package com.aarshinkov.masters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String recipes(Model model) {
        model.addAttribute("globalMenu", "recipes");

        return "recipes/recipes";
    }
}
