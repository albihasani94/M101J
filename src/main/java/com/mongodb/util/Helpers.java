package com.mongodb.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;
import org.bson.json.JsonMode;
import org.bson.json.JsonWriter;
import org.bson.json.JsonWriterSettings;

import java.io.StringWriter;
import java.util.function.Consumer;

public class Helpers {
    public static void printJson(Document document) {
        JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                new JsonWriterSettings(JsonMode.SHELL, false));

        new DocumentCodec().encode(jsonWriter, document,
                EncoderContext.builder()
                        .isEncodingCollectibleDocument(true)
                        .build());
        System.out.println(jsonWriter.getWriter());
        System.out.flush();
    }

    public static Consumer<Document> jsonToString() {
        return document -> {
            JsonWriter jsonWriter = new JsonWriter(new StringWriter(),
                    new JsonWriterSettings(JsonMode.SHELL, false));

            new DocumentCodec().encode(jsonWriter, document,
                    EncoderContext.builder()
                            .isEncodingCollectibleDocument(true)
                            .build());
            System.out.println(jsonWriter.getWriter());
            System.out.flush();
        };
    }

    public static MongoCollection<Document> getDocumentMongoCollection(String collection) {
        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        return database.getCollection(collection);
    }
}
