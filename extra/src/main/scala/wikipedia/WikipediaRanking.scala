package wikipedia

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import scala.collection.immutable.List

object WikipediaRanking {

  val conf: SparkConf = new SparkConf().
    setMaster("local[2]").
    setAppName("CountingSheep")

  val sc: SparkContext = new SparkContext(conf)

  val myRdd: RDD[(Int, String)] = sc.parallelize(List((1, "one"), (2, "two"), (3, "three")))

  def perform(rdd: RDD[(Int, String)]) =
    rdd.collect()

  def main(args: Array[String]) {
    val result = perform(myRdd)
    result.foreach(r => println(s"($r._1, $r._2)"))

    sc.stop()
  }

}
