package com.mongodb.hw32;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlogPostDAO {
    DBCollection postsCollection;

    public BlogPostDAO(final DB blogDatabase) {
        postsCollection = blogDatabase.getCollection("posts");
    }

    // Return a single post corresponding to a permalink
    public DBObject findByPermalink(String permalink) {

        DBObject post = null;
        BasicDBObject query = new BasicDBObject("permalink", permalink);
        post = postsCollection.findOne(query);


        return post;
    }

    // Return a list of posts in descending order. Limit determines
    // how many posts are returned.
    public List<DBObject> findByDateDescending(int limit) {

        List<DBObject> posts = null;

        BasicDBObject sort = new BasicDBObject("date", -1);
        DBCursor cursor = postsCollection.find().sort(sort).limit(limit);
        while (cursor.hasNext()) {
            if (posts == null)
                posts = new ArrayList<DBObject>();
            DBObject singlePost = cursor.next();
            posts.add(singlePost);
        }

        return posts;
    }


    public String addPost(String title, String body, List tags, String username) {

        System.out.println("inserting blog entry " + title + " " + body);

        String permalink = title.replaceAll("\\s", "_"); // whitespace becomes _
        permalink = permalink.replaceAll("\\W", ""); // get rid of non alphanumeric
        permalink = permalink.toLowerCase();


        BasicDBObject post = new BasicDBObject();

        post.append("author", username).append("title", title).append("body", body).append("permalink", permalink)
                .append("tags", tags).append("comments", new ArrayList<String>()).append("date", new Date());

        postsCollection.insert(post);

        return permalink;
    }


    // White space to protect the innocent


    // Append a comment to a blog post
    public void addPostComment(final String name, final String email, final String body,
                               final String permalink) {

        BasicDBObject query = new BasicDBObject("permalink", permalink);
        DBObject post = postsCollection.findOne(query);
        ArrayList<BasicDBObject> comments = (ArrayList) post.get("comments");

        BasicDBObject comment = new BasicDBObject("author", name).append("body", body);
        if (email != null) {
            comment.append("email", email);
        }

        comments.add(comment);

        post.put("comments", comments);

        postsCollection.update(new BasicDBObject("_id", post.get("_id")), post, true, false);


    }


}