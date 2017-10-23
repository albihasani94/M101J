package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloSparkFreemarker {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloFreemarker.class, "/");

        Spark.get(new Route("/") {
            public Object handle(Request request, Response response) {
                StringWriter writer = new StringWriter();
                try {
                    Template template = configuration.getTemplate("hello.ftl");

                    Map<String, Object> helloMap = new HashMap<>();
                    helloMap.put("name", "Freemarker");

                    template.process(helloMap, writer);
                    System.out.println(writer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }

}
