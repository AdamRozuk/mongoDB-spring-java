package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.repositiory.FilmRepository;
import com.example.mongoDB.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private final DatabaseService dbService;
    private final FilmRepository repository;

    @Autowired
    public Controller(DatabaseService dbService, FilmRepository repository) {
        this.dbService = dbService;
        this.repository = repository;
    }

    @RequestMapping(value = "/home")
    public String start() {
        return "index";
    }

    @GetMapping
    @RequestMapping("/show2")
    public String show2(Model model) {
        List<String> show2 = dbService.showDatabaseCollection();
        model.addAttribute("show2", show2);
        return "show2";
    } //show 2 to poprzednie wyświetlanie.

    @GetMapping
    @RequestMapping("/showFindByCategory")
    public String showFindByCategory(Model model) {
        List<Film> showFindByCategory = dbService.showFindByCategory();
        model.addAttribute("showFindByCategory", showFindByCategory);
        return "showFindByCategory";
    }


}
