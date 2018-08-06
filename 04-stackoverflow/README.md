# Practice Programming Assignment: StackOverflow

Download resources:

```shell
wget http://alaska.epfl.ch/~dockermoocs/bigdata/stackoverflow.csv -P src/main/resources/stackoverflow
```

Run:

```shell
sbt test
sbt run
```

### Schema

Each line in the provided text file has the following format:
```
<postTypeId>,<id>,[<acceptedAnswer>],[<parentId>],<score>,[<tag>]
```

A short explanation of the comma-separated fields follows.

```
<postTypeId>:     Type of the post. Type 1 = question,
                  type 2 = answer.

<id>:             Unique id of the post (regardless of type).

<acceptedAnswer>: Id of the accepted answer post. This
                  information is optional, so maybe be missing
                  indicated by an empty string.

<parentId>:       For an answer: id of the corresponding
                  question. For a question:missing, indicated
                  by an empty string.

<score>:          The StackOverflow score (based on user
                  votes).

<tag>:            The tag indicates the programming language
                  that the post is about, in case it's a
                  question, or missing in case it's an answer.
```

Documentation:

1. lines: the lines from the csv file as strings
2. raw: the raw Posting entries for each line
3. grouped: questions and answers grouped together
4. scored: questions and scores
5. vectors: pairs of (language, score) for each question

### Reference

https://www.coursera.org/learn/scala-spark-big-data/programming/QhzMw/stackoverflow-2-week-long-assignment
