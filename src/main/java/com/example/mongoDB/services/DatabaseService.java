package com.example.mongoDB.services;

import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {
    final String uriString = "mongodb://localhost:27017";
    private MongoClient mongoClient;
    private MongoCollection<Document> collection;

    public void setUpDatabaseConnection(){
        mongoClient = MongoClients.create(uriString);
        MongoDatabase mongoDB = mongoClient.getDatabase("myDB");
        collection = mongoDB.getCollection("Film");
    }

    public void showDatabaseCollection(){
        this.setUpDatabaseConnection();
        FindIterable<Document> findIterable = collection.find(new Document());

        for (Document doc: findIterable) {
            System.out.println(doc.toJson());
        }

        mongoClient.close();
    }

    public void addRecordToDatabase(){
        this.setUpDatabaseConnection();

        Document film = new Document("title", "Spring Boot")
                .append("year", 2018)
                .append("category", "Drama");

        collection.insertOne(film);
        mongoClient.close();
    }
}
