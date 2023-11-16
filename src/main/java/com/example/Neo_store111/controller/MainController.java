package com.example.Neo_store111.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/to")
    public class MainController {

    @GetMapping("/main")
    public String mainC() {
        return "main";
    }
}
