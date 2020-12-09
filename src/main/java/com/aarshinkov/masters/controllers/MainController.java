package com.aarshinkov.masters.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping("/")
    public String recipes(Model model) {
        model.addAttribute("globalMenu", "recipes");

        return "recipes/recipes";
    }

    @GetMapping("/recipe/{recipeId}")
    public String recipe(@PathVariable("recipeId") Long recipeId, Model model) {
        model.addAttribute("recipeId", recipeId);
        model.addAttribute("globalMenu", "recipes");

        return "recipes/recipe";
    }

    @GetMapping("/login")
    @PreAuthorize("!isAuthenticated()")
    public String login(Model model) {
        model.addAttribute("globalMenu", "login");

        return "login";
    }

    @GetMapping("/signup")
    @PreAuthorize("!isAuthenticated()")
    public String signup(Model model) {
        model.addAttribute("globalMenu", "signup");

        return "signup";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("globalMenu", "profile");
        model.addAttribute("submenu", "profile");

        return "personal/profile";
    }
}
