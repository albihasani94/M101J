package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static com.mongodb.util.Helpers.getDocumentMongoCollection;

public class HelloSparkFreemarker {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloFreemarker.class, "/");

        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("course");
        database.createCollection("sparkFreeMarker");
        MongoCollection<Document> sparkFreemarker = database.getCollection("sparkFreemarker");

        sparkFreemarker.drop();

        sparkFreemarker.insertOne(new Document("name", "Test"));

        Spark.get(new Route("/") {
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template template = configuration.getTemplate("hello.ftl");
                    Document document = sparkFreemarker.find().first();
                    template.process(document, writer);
                    System.out.println(writer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }

}
