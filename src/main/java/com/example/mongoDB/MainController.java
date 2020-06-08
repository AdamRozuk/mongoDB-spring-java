package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {

    private final DatabaseService dbService;

    @Autowired
    public MainController(DatabaseService dbService) {
        this.dbService = dbService;
    }

    @GetMapping
    @RequestMapping("/show")
    public List<String> show(){
        return dbService.showDatabaseCollection();
    }

    @PostMapping
    @RequestMapping("/addFilm")
    public void addFilm(@RequestBody Film film ){
        dbService.addRecordToDatabase(film);
    }

}

