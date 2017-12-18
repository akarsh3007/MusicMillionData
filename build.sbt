import sun.security.tools.PathList

name := "songs"

version := "0.1"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0"

libraryDependencies += "org.apache.spark" % "spark-mllib_2.11" % "2.1.0"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.1.0"


libraryDependencies ++= Seq("com.typesafe" % "config" % "1.2.0", "org.apache.spark" % "spark-hive_2.11" % "2.1.0")

libraryDependencies += "org.apache.spark" % "spark-repl_2.11" % "2.1.0"

// https://mvnrepository.com/artifact/com.typesafe/config
//libraryDependencies += "com.typesafe" % "config" % "1.3.1"
unmanagedClasspath in Runtime +=baseDirectory.value /"resources"
