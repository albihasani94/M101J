package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.util.Helpers.getDocumentMongoCollection;
import static com.mongodb.util.Helpers.jsonToString;

public class FindTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection("insertTest");

       // System.out.println(collection.find().first());

        List<Document> all = collection.find().into(new ArrayList<>());
        all.forEach(jsonToString());
    }
}
