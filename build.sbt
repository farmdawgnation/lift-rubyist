organization := "me.frmr"

name := "lift-rubyist_2.6"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++ = {
  val liftVersion = "2.6+"
  "net.liftweb" %% "lift-webkit" % liftVersion % "compile",
  "org.jruby" % "jruby" % "1.7.4",
  "org.scalatest" %% "scalatest" % "2.0" % "test"
}
