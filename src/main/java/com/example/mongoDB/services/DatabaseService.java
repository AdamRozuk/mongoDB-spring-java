package com.example.mongoDB.services;

import com.example.mongoDB.entities.Film;
import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;
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
        collection = mongoDB.getCollection("film");
    }

    public List<String> showDatabaseCollection(){
        this.setUpDatabaseConnection();
        FindIterable<Document> findIterable = collection.find();
        list.clear();
        for (Document doc: findIterable) {
            System.out.println(doc.toJson());
            list.add(String.valueOf(doc.get("_id", ObjectId.class)));
            list.add((String) doc.get("title"));
            list.add(String.valueOf(doc.get("year")));
            list.add((String) doc.get("category"));
        }

        mongoClient.close();
        return list;
    }

    public void addRecordToDatabase(Film film){
        this.setUpDatabaseConnection();

        Document doc= new Document("title", film.title)
                .append("year", film.year)
                .append("category", film.category);
        collection.insertOne(doc);
        mongoClient.close();
    }
}
