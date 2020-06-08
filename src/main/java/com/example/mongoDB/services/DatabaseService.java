package com.example.mongoDB.services;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabaseService {
    final String uriString = "mongodb://localhost:27017";
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;
    private List<String> list = new ArrayList<>();

    public void setUpDatabaseConnection(){
        mongoClient = MongoClients.create(uriString);
        MongoDatabase mongoDB = mongoClient.getDatabase("myDB");
        collection = mongoDB.getCollection("Film");
    }

    public List<String> showDatabaseCollection(){
        this.setUpDatabaseConnection();
        FindIterable<Document> findIterable = collection.find(new Document());

        for (Document doc: findIterable) {
            System.out.println(doc.toJson());
            list.add(doc.toJson());
        }
        mongoClient.close();
        return list;
    }

    public void addRecordToDatabase(){
        this.setUpDatabaseConnection();

        Document film = new Document("title", "The Greatest")
                .append("year", 2000)
                .append("category", "Comedy");

        collection.insertOne(film);
        mongoClient.close();
    }
}
