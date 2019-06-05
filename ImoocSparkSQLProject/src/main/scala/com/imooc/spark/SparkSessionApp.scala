package com.imooc.spark

import org.apache.spark.sql.SparkSession

/**
 * SparkSession的使用
 */
object SparkSessionApp {

  def main(args: Array[String]) {

//    val sparkconf = new SparkConf()
//    sparkconf.appname("SparkSessionApp")
//    saprkconf.master("local[1]")
    val spark = SparkSession.builder().appName("SparkSessionApp")
      .master("local[2]").getOrCreate()

    val people = spark.read.json("D:/file/people.json")
    people.show(500)

    spark.stop()
  }
}
