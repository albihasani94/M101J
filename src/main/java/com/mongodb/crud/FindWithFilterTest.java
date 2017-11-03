package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.util.Helpers.jsonToString;

import java.util.ArrayList;
import java.util.List;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        MongoCollection<Document> collection = database.getCollection("insertTest");

        Bson filter = new Document("age", new Document("$gt", 25));

        List<Document> all = collection.find(filter).into(new ArrayList<>());
        all.forEach(jsonToString());
    }
}
