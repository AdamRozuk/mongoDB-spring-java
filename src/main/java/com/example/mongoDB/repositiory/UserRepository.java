package com.example.mongoDB.repositiory;

import com.example.mongoDB.entities.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Film, String> {


}