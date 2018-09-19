package com.formento.kafkasparkstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.types._

/*
produce messages like:
id_1|{"query": "iron maiden", "hits": 666, "results": ["somewhere in time", "powerslave", "killers"]}
id_2|{"query": "seventh son of seventh son", "hits": 12, "results": ["iron maiden", "album", "killers"]}
*/

object JsonExampleApp extends App {
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

  spark.udf.register("udf_first_result_of_list", udf { list: Seq[String] => new Udfs().firstResultOfList(list) })

  val expression = df.selectExpr("CAST(key AS STRING) as key", "CAST(value AS STRING) as value")
    .as[(String, String)]

  expression.createOrReplaceTempView("search")

  // get_json_object(value, '$.example.myList')

  val table = spark.sql(
    """
    select key,
           value,
           get_json_object(value, '$.results') as result_list--,
           --udf_first_result_of_list(get_json_object(value, '$.results')) as first_result
      from search
      group by key, value
    """)


  val console = table.writeStream
    .outputMode("complete")
    .format("console")
    .start()

  console.awaitTermination()

  class Udfs extends Serializable {

    def firstResultOfList(list: Seq[String]) = list.headOption.getOrElse("")

  }

}
