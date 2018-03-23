name := """schedulingsystem"""
organization := "SchedulingSystem"

version := "1.0-SNAPSHOT"

lazy val root = Project( id = "schedulingsystem", base = file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.12.4"

libraryDependencies ++= Seq(
  "io.swagger" %% "swagger-play2" % "1.6.0",
  "org.glassfish.web" % "javax.el" % "2.2.6",
  "mysql" % "mysql-connector-java" % "5.1.36",
  "javax.el" % "javax.el-api" % "3.0.0",
  guice
)