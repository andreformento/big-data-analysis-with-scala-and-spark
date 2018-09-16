# Spark streaming

_If you want to use an IDE to run examples, maybe you need see in your IDE how to `include dependency with 'provided' scope`_

### Run a simple app (before spark streaming):

Local running:

```shell
sbt run
# and choose number of CountingLocalApp class
```

Or use parameters `sbt "run inputFile.txt /outputPath"`

## Now run Spark Streaming Apps :)

### Simple example with streaming

[Reference](https://spark.apache.org/docs/latest/structured-streaming-programming-guide.html#quick-example)

Before all, run in separated tab terminal:
```shell
nc -lk 9999
# write some words and press ENTER
```

Now start Spark Streaming:
```shell
# and choose number of SparkStreamingApp class
```

### Streaming datasets

[Reference](https://spark.apache.org/docs/latest/structured-streaming-programming-guide.html#creating-streaming-dataframes-and-streaming-datasets)

Now start Spark Streaming:
```shell
# and choose number of StreamingDatasetsApp class
```

#### Example of a project creation

```shell
sbt new holdenk/sparkProjectTemplate.g8 \
        --name=sparkstreaming \
        --organization=com.formento.sparkstreaming \
        --sparkVersion=2.3.1
```

go inside `cd sparkstreaming` and run :)

### Reference

- https://spark.apache.org/docs/2.3.1/structured-streaming-programming-guide.html
- https://github.com/MrPowers/spark-sbt.g8
- http://www.foundweekends.org/giter8/
- https://www.scala-sbt.org/1.x/docs/sbt-new-and-Templates.html
- https://medium.com/@mrpowers/creating-a-spark-project-with-sbt-intellij-sbt-spark-package-and-friends-cc9108751c28
- https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html
