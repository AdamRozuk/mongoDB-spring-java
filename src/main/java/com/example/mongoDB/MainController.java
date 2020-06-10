package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.repositiory.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MainController {

    private final FilmRepository repository;

    @Autowired
    public MainController(FilmRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @RequestMapping("/addFilm")
    public void addFilm(@RequestBody Film film ){
        repository.save(film);
    }

    @GetMapping
    @RequestMapping("/test")
    public void test() {
        System.out.println("find all films");
        for (Film film: repository.findAll()) {
            System.out.println(film);
        }
        System.out.println("Find by name");
        System.out.println(repository.findByTitle("RepositoryXX"));
    }

}

