name := "error-handling"

scalaVersion := "0.27.0-RC1"

libraryDependencies ++=
  Seq(
    "org.scala-js"   % "scalajs-dom_sjs1_2.13" % "1.1.0",
    "org.scalameta" %% "munit"                 % "0.7.12" % Test,
    "org.scalameta" %% "munit-scalacheck"      % "0.7.12" % Test
  )

testFrameworks += new TestFramework("munit.Framework")

//enablePlugins(MyScalaJSPlugin)

run / fork := true
