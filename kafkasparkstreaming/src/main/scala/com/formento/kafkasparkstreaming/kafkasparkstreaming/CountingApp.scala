package com.formento.kafkasparkstreaming.kafkasparkstreaming

import org.apache.spark.{SparkConf, SparkContext}

object CountingLocalApp extends App{
  val (inputFile, outputFile) = (args(0), args(1))
  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("my awesome app")

  Runner.run(conf, inputFile, outputFile)
}

object Runner {
  def run(conf: SparkConf, inputFile: String, outputFile: String): Unit = {
    /*
    // Subscribe to 1 topic
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "host1:port1,host2:port2")
      .option("subscribe", "topic1")
      .load()
    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]

    // Subscribe to multiple topics
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "host1:port1,host2:port2")
      .option("subscribe", "topic1,topic2")
      .load()
    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]

    // Subscribe to a pattern
    val df = spark
      .readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "host1:port1,host2:port2")
      .option("subscribePattern", "topic.*")
      .load()
    df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)")
      .as[(String, String)]*/


    val sc = new SparkContext(conf)
    val rdd = sc.textFile(inputFile)
    val counts = WordCount.withStopWordsFiltered(rdd)
    counts.saveAsTextFile(outputFile)
  }
}
