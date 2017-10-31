### HW 2.2. What is the student_id of the lowest exam score above 65?

```sql
db.grades.find( {score: {$gte : 65} } ).sort( {score:1})
```

### HW 2.3. Provide the identity of the student with the highest average in the class using the aggregation framework.

```sql
db.grades.aggregate( 
    { $match: { type: "exam" } },
    { '$group' : { '_id' : '$student_id', 'average' : { $avg : '$score' } }}, 
    { '$sort' : { 'average' : -1 } }, 
    { '$limit' : 1 } 
)
```

### HW 2.5. Which of the choices below is the title of a movie from the year 2013 that is rated PG-13 and won no awards? 

```sql
db.movieDetails.find( { $and: [{"year":2013}, {"rated":"PG-13"}, {"awards.wins":0}] }).pretty()
```

### HW 2.6. Using the video.movieDetails collection, how many movies list "Sweden" second in the the list of countries.

```sql
db.movieDetails.find( { "countries.1" : "Sweden" } ).count()
```