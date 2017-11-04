package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.util.Helpers.getDocumentMongoCollection;

public class UpdateTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection();

        collection.replaceOne(eq("age", 27), new Document("name", "Tefta")
                .append("age", 27)
                .append("profession", "Software Gardener"));

        collection.updateOne(eq("name", "Tefta"), new Document("$set", new Document("age", 33)));
    }
}
