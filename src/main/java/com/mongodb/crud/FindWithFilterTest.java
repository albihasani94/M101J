package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.util.Helpers.getDocumentMongoCollection;
import static com.mongodb.util.Helpers.jsonToString;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection("insertTest");

        //Bson filter = new Document("age", new Document("$gt", 25));
        Bson filter = and(gte("age", 30), eq("name", "Aqif"));

        List<Document> all = collection.find(filter).into(new ArrayList<>());
        all.forEach(jsonToString());
    }
}
