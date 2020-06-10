package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.repositiory.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FilmController {
    private final FilmRepository repository;

    @Autowired
    public FilmController(FilmRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/home")
    public String start() {
        return "index";
    }

    @GetMapping
    @RequestMapping("/showFindByCategory")
    public String showFindByCategory(Model model) {
        List<Film> showFindByCategory = repository.findByCategory("Drama");
        model.addAttribute("showFindByCategory", showFindByCategory);
        return "showFindByCategory";
    }

}
