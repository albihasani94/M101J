package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.util.Helpers.getDocumentMongoCollection;
import static com.mongodb.util.Helpers.jsonToString;

public class FindWithSortSkipLimitTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection("insertTest");

        Bson sort = ascending("age");

        List<Document> all = collection.find()
                .sort(sort)
                .skip(1)
                .limit(1)
                .into(new ArrayList<>());

        all.forEach(jsonToString());
    }
}
