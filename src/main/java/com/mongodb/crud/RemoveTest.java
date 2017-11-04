package com.mongodb.crud;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.util.Helpers.getDocumentMongoCollection;

public class RemoveTest {
    public static void main(String[] args) {
        MongoCollection<Document> collection = getDocumentMongoCollection();

        collection.deleteOne(eq("_id", new ObjectId("59fde79595b4ff2fa0c44e95")));
    }
}
