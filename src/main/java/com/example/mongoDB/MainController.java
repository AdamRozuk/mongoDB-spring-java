package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.repositiory.FilmRepository;
import com.example.mongoDB.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private final DatabaseService dbService;
    private final FilmRepository repository;

    @Autowired
    public MainController(DatabaseService dbService, FilmRepository repository) {
        this.dbService = dbService;
        this.repository = repository;
    }

    @PostMapping
    @RequestMapping("/addFilm")
    public void addFilm(@RequestBody Film film ){
        dbService.addRecordToDatabase(film);
    }

    @GetMapping
    @RequestMapping("/test")
    public void test() {
        repository.save(new Film("Repository",2000,"Drama"));
        System.out.println("find all films");
        for (Film film: repository.findAll()) {
            System.out.println(film);
        }
        System.out.println("Find by name");
        System.out.println(repository.findByTitle("RepositoryXX"));
    }

}

