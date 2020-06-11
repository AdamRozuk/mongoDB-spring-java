package com.example.mongoDB;

import com.example.mongoDB.entities.Film;
import com.example.mongoDB.repositiory.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MainController {

    private final FilmRepository repository;

    @Autowired
    public MainController(FilmRepository repository) {
        this.repository = repository;
    }

    @PutMapping
    @RequestMapping("/updateFilm")
    public void updateFilm(@RequestBody Film film, @RequestParam String id){
        Optional<Film> filmToUpdate = repository.findById(id);
        if(filmToUpdate.isPresent()){
            filmToUpdate.get().setTitle(film.getTitle());
            filmToUpdate.get().setYear(film.getYear());
            filmToUpdate.get().setCategory(film.getCategory());
        }
        filmToUpdate.ifPresent(repository::save);
    }

}

