package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FilmController {
    private final DatabaseService dbService;

    @Autowired
    public FilmController(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @RequestMapping(value = "/home")
    public String start() {
        return "index";
    }

    @GetMapping
    @RequestMapping("/showFindByCategory")
    public String showFindByCategory(Model model) {
        List<Film> showFindByCategory = dbService.showFindByCategory();
        model.addAttribute("showFindByCategory", showFindByCategory);
        return "showFindByCategory";
    }

}
