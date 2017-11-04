package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.util.Helpers.getDocumentMongoCollection;
import static com.mongodb.util.Helpers.jsonToString;

public class FindWithProjectionTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection();

        Bson projection = Projections.fields(include("name", "profession"),
                exclude("_id"));

        List<Document> all = collection.find().projection(projection).into(new ArrayList<>());

        all.forEach(jsonToString());
    }
}
