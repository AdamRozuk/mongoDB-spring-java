package com.example.mongoDB;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @RequestMapping("/home")
    public String showFilms(){
        return "Films";
    }
}

