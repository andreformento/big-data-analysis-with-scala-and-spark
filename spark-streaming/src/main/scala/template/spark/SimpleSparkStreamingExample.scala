package template.spark

import org.apache.spark.sql.functions._

object SimpleSparkStreamingExample extends InitSpark {
  def main(args: Array[String]) = {
    import spark.implicits._

    // Create DataFrame representing the stream of input lines from connection to localhost:9998
    val lines = spark.readStream
      .format("socket")
      .option("host", "localhost")
      .option("port", 9998)
      .load()

    // Split the lines into words
    val words = lines.as[String].flatMap(_.split(" "))

    // Generate running word count
    val wordCounts = words.groupBy("value").count()

    // Start running the query that prints the running counts to the console
    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()

    println("Run `nc -lk 9998`")

    println("Press ENTER to finish")
    scala.io.StdIn.readLine()

    close
  }
}
