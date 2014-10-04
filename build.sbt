organization := "me.frmr"

name := "lift-rubyist_2.6"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= {
  Seq(
    "net.liftweb" %% "lift-webkit" % "2.6-RC1" % "compile",
    "org.jruby" % "jruby" % "1.7.16",
    "org.scalatest" %% "scalatest" % "2.2.1" % "test",
    "javax.servlet"     %  "servlet-api"        % "2.5" % "provided"
  )
}
