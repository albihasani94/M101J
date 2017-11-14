### HW 4.1. Which of the following queries can utilize at least one index to find all matching documents, or to sort? 
Check all that apply.
```sql
db.products.find({brand:"GE"}).sort({price:1})
db,products.find({$and:[{price:{$gt:30}}, {price:{$lt:50}}]}).sort({brand:1})
```

### HW 4.2. Suppose you have a collection called tweets whose documents contain information about  the created_at time of the tweet and the user's followers_count at the time they issued the tweet. 
What can you infer from the following explain output?

```sql
The query uses an index to determine the order in which to return the result documents
The query examines 251120 documents
```