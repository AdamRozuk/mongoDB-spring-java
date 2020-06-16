package com.example.mongoDB.repositiory;

import com.example.mongoDB.entities.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends MongoRepository<Film, String> {

    List<Film> findByCategory(String category);
    List<Film> findByTitle(String title);

}
