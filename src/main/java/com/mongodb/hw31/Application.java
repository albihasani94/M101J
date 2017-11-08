package com.mongodb.hw31;

import com.mongodb.*;

import java.util.ArrayList;

public class Application {
    public static void main(String[] args) {
        MongoClient client = new MongoClient();
        DB db = client.getDB("school");
        DBCollection collection = db.getCollection("students");

        BasicDBObject query = new BasicDBObject();
        DBCursor cursor = collection.find(query);
        try {
            while (cursor.hasNext()) {
                DBObject lowest = null;
                DBObject object = cursor.next();
                System.out.println(" Student " + object);
                ArrayList<BasicDBObject> scores = (ArrayList) object.get("scores");
                for (BasicDBObject score : scores) {
                    if (score.get("type").toString().equalsIgnoreCase("homework")) {
                        if (lowest == null) lowest = score;
                        Float scoreLocal = Float.parseFloat(score.get("score").toString());
                        Float lowestLocal = Float.parseFloat(lowest.get("score").toString());
                        System.err.println("scoreLocal : " + scoreLocal + " lowestLocal: " + lowestLocal);
                        if (scoreLocal < lowestLocal) lowest = score;
                    }
                }
                System.err.println("Min: " + lowest);
                scores.remove(lowest);
                object.put("scores", scores);
                collection.update(new BasicDBObject("_id", object.get("_id")), object, true, false);
            }
        } finally {
            cursor.close();
        }
    }
}
