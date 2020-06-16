package com.example.mongoDB.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
public class User {

    @Id
    public String nickname;
    public List<String> LikedFilmsId;
    public String role;

    public User(String nickname) {
        this.nickname = nickname;
    }

}
