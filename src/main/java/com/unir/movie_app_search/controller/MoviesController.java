package com.unir.movie_app_search.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MoviesController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
