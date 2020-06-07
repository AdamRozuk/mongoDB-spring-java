package com.example.mongoDB;

import com.example.mongoDB.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final DatabaseService dbService;

    @Autowired
    public MainController(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @RequestMapping("/home")
    public String showFilms(){
        dbService.showDatabaseCollection();
        return "Show Films";
    }

    @RequestMapping("/addFilm")
    public String addFilms(){
        dbService.addRecordToDatabase();
        return "Add record to Database";
    }
}

