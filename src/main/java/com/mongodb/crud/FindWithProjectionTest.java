package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.util.Helpers.getDocumentMongoCollection;
import static com.mongodb.util.Helpers.jsonToString;

public class FindWithProjectionTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection();

        Bson projection = new Document("age", 1).append("name", 1);

        List<Document> all = collection.find().projection(projection).into(new ArrayList<>());

        all.forEach(jsonToString());
    }
}
