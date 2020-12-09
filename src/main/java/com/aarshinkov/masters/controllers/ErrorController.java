package com.aarshinkov.masters.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    @GetMapping("/404")
    public String error404() {
        return "errors/404";
    }

    @GetMapping("/error")
    public String error() {
        return "errors/error";
    }

    @GetMapping("/403")
    public String error403() {
        return "errors/403";
    }
}
