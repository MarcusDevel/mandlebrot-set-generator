name := "mandlebrot-set"

version := "0.1"

scalaVersion := "2.13.1"

//http://www.scalatest.org/install
libraryDependencies += "org.scalactic" %% "scalactic" % "3.1.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.1" % "test"

//enable par collections since deprecation from core language
libraryDependencies +=
  "org.scala-lang.modules" %% "scala-parallel-collections" % "0.2.0"