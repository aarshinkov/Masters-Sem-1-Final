package com.aarshinkov.masters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

//    @GetMapping("/")
//    public String home(Model model) {
//        model.addAttribute("globalMenu", "home");
//
//        return "home";
//    }

    @GetMapping("/")
    public String recipes(Model model) {
        model.addAttribute("globalMenu", "recipes");

        return "food/recipes";
    }

    @GetMapping("/recipe/{recipeId}")
    public String recipe(@ModelAttribute("recipeId") @PathVariable("recipeId") Long recipeId, Model model) {
        model.addAttribute("globalMenu", "recipes");

        return "food/recipe";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("globalMenu", "login");

        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("globalMenu", "signup");

        return "signup";
    }
}
