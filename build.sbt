name := """schedulingsystem"""
organization := "SchedulingSystem"

version := "1.0-SNAPSHOT"

lazy val root = Project( id = "schedulingsystem", base = file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"
