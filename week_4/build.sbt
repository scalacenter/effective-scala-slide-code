name := "error-handling"

scalaVersion := "3.0.0-M1"

libraryDependencies ++=
  Seq(
    "org.scala-js"   % "scalajs-dom_sjs1_2.13" % "1.1.0",
    "org.scalameta" %% "munit"                 % "0.7.16" % Test,
    "org.scalameta" %% "munit-scalacheck"      % "0.7.16" % Test
  )

testFrameworks += new TestFramework("munit.Framework")

//enablePlugins(MyScalaJSPlugin)

run / fork := true
