package com.mongodb;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * @author ahasani
 */
public class HelloSpark {

    public static void main(String[] args) {
        Spark.get(new Route("/") {
            public Object handle(Request request, Response response) {
                return "Hello Spark";
            }
        });
    }
}
