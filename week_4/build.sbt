name := "error-handling"

scalaVersion := "3.0.0-RC2"

libraryDependencies ++=
  Seq(
    ("org.scala-js" %%% "scalajs-dom" % "1.1.0").cross(CrossVersion.for3Use2_13),
    "org.scalameta" %% "munit"                 % "0.7.23" % Test,
    "org.scalameta" %% "munit-scalacheck"      % "0.7.23" % Test
  )

testFrameworks += new TestFramework("munit.Framework")

enablePlugins(ScalaJSPlugin)
scalaJSUseMainModuleInitializer := true

run / fork := true
