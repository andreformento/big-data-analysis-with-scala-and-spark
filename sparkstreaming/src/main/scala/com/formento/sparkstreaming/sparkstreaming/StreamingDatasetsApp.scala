package com.formento.sparkstreaming.sparkstreaming

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.StructType

import scala.reflect.io.File

object StreamingDatasetsApp extends App {
  val spark = SparkSession
    .builder
    .appName("StructuredNetworkWordCount")
    .master("local[2]")
    .getOrCreate()

  // Read text from socket
  val socketDF = spark
    .readStream
    .format("socket")
    .option("host", "localhost")
    .option("port", 9999)
    .load()

  socketDF.isStreaming    // Returns True for DataFrames that have streaming sources

  socketDF.printSchema

  // Read all the csv files written atomically in a directory
  val csvFileName = File("src/main/resources/streaming-datasets.csv").jfile.getAbsolutePath
  println(s"file: $csvFileName")

  val userSchema = new StructType().add("name", "string").add("age", "integer")

  val csvDF = spark
    .readStream
    .option("sep", ",")
    .option("delimiter", ",")
    .schema(userSchema)      // Specify schema of the csv files
    .csv(csvFileName)    // Equivalent to format("csv").load("/path/to/directory")
}
