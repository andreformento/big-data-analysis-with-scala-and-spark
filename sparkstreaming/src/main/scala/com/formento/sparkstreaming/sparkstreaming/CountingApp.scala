package com.formento.sparkstreaming.sparkstreaming

import java.io.File

import org.apache.commons.io.FileUtils
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Use this to test the app locally, from sbt:
  * sbt "run inputFile.txt outputFile.txt"
  * (+ select CountingLocalApp when prompted)
  */
object CountingLocalApp extends App {
  val (inputFile, outputFile) =
    args.length match {
      case 2 => (args(0), args(1))
      case _ => ("src/main/resources/inputFile.txt", "src/main/resources/output")
    }


  val conf = new SparkConf()
    .setMaster("local")
    .setAppName("my awesome app")

  Runner.run(conf, inputFile, outputFile)
}

/**
  * Use this when submitting the app to a cluster with spark-submit
  **/
object CountingApp extends App {
  val (inputFile, outputFile) = (args(0), args(1))

  // spark-submit command should supply all necessary config elements
  Runner.run(new SparkConf(), inputFile, outputFile)
}

object Runner {
  private def validateFiles(inputFile: String, outputPath: String): Unit = {
    if (!new java.io.File(inputFile).exists) throw new IllegalArgumentException(s"Input file does not exists: $inputFile")
    FileUtils.deleteDirectory(new File(outputPath))
  }

  def run(conf: SparkConf, inputFile: String, outputPath: String): Unit = {
    validateFiles(inputFile, outputPath)

    val sc = new SparkContext(conf)
    val rdd = sc.textFile(inputFile)
    val counts = WordCount.withStopWordsFiltered(rdd)
    counts.saveAsTextFile(outputPath)
  }
}
