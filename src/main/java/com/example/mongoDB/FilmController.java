package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.repositiory.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FilmController {
    private final FilmRepository repository;

    @Autowired
    public FilmController(FilmRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = "/home")
    public String index(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "category", required = false) String category
    ) {
        if (title != null && year != null && category != null){
            repository.insert(new Film(title,year,category));
        }
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
