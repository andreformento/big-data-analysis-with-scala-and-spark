package template.spark

import org.apache.spark.sql.functions._

final case class Person(firstName: String, lastName: String,
                        country: String, age: Int)

object SimpleExample extends InitSpark {
  def main(args: Array[String]) = {
    import spark.implicits._

    val version = spark.version
    println("SPARK VERSION = " + version)

    val sumHundred = spark.range(1, 101).reduce(_ + _)
    println(f"Sum 1 to 100 = $sumHundred")

    println("Reading from csv file: people-example.csv")
    val persons = reader.csv("people-example.csv").as[Person]
    persons.show(2)
    val averageAge = persons.agg(avg("age"))
                     .first.get(0).asInstanceOf[Double]
    println(f"Average Age: $averageAge%.2f")

    println("Press ENTER to finish")
    scala.io.StdIn.readLine()

    close
  }
}
