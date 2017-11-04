package com.mongodb.crud;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.util.Helpers.getDocumentMongoCollection;
import static com.mongodb.util.Helpers.jsonToString;

import java.util.ArrayList;
import java.util.List;

public class FindWithFilterTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection();

        //Bson filter = new Document("age", new Document("$gt", 25));
        Bson filter = Filters.and(Filters.gte("age", 30), Filters.eq("name", "Aqif"));

        List<Document> all = collection.find(filter).into(new ArrayList<>());
        all.forEach(jsonToString());
    }
}
