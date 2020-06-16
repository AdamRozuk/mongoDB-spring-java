package com.example.mongoDB.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Film {

    @Id
    public String id;
    public String title;
    public Integer year;
    public String category;
    public Integer likes;

    public Film(String title, Integer year, String category) {
        this.title = title;
        this.year = year;
        this.category = category;
        likes=0;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", category='" + category + '\'' +
                ", like='" + likes + '\'' +
                '}';
    }
}
