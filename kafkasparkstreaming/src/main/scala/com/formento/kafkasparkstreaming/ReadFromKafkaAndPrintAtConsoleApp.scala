package com.formento.kafkasparkstreaming

import org.apache.spark.sql.SparkSession

object ReadFromKafkaAndPrintAtConsoleApp extends App {
  val spark = SparkSession
    .builder
    .appName("KafkaStreamingApp")
    .master("local[2]")
    .getOrCreate()

  import spark.implicits._

  // Subscribe to 1 topic
  val df = spark
    .readStream
    .format("kafka")
    .option("kafka.bootstrap.servers", "localhost:9092")
    .option("subscribe", "topic1")
    .load()
  val expression = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    .as[(String, String)]

  expression.createOrReplaceTempView("mytable")

  val table = spark.sql("select key, value, concat(key, value) from mytable")

  val console = table.writeStream
    .format("console")
    .start()

  console.awaitTermination()

}
