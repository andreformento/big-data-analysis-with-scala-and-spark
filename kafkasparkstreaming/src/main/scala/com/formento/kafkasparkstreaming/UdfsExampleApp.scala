package com.formento.kafkasparkstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf

/*
produce messages like:
10|iron maiden
20|seventh son of seventh son
30|when the wild wind blows
40|if eternity should fail
*/

object UdfsExampleApp extends App {
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

  spark.udf.register("udf_count_words", udf { text: String => new Udfs().countWords(text) })

  val expression = df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
    .as[(String, String)]

  expression.createOrReplaceTempView("book")

  val table = spark.sql(
    """
    select key,
           value,
           udf_count_words(value) AS count_words
      from book
    """)

  // get_json_object(value,'$.example.myList')

  val console = table.writeStream
    .format("console")
    .start()

  console.awaitTermination()

  class Udfs extends Serializable {

    def countWords(text: String) = text.split(" ").length

  }

}
