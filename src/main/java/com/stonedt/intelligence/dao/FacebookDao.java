package com.stonedt.intelligence.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacebookDao {
    @Autowired
    public MongoTemplate mongoTemplate;

    public List<Document> find(){
        Query query = new Query();
        List<Document> docs = mongoTemplate.find(query, Document.class, "test.army");
        return docs;
    }
}
