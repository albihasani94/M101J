package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import static com.mongodb.util.Helpers.getDocumentMongoCollection;

public class InsertTest {
    public static void main(String[] args) {
        MongoCollection<Document> coll = getDocumentMongoCollection("insertTest");

        Document aqif = new Document("name", "Aqif")
                .append("age", 30)
                .append("profession", "Software Develoepr");

        coll.insertOne(aqif);
    }
}
