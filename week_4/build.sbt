name := "error-handling"

scalaVersion := "3.0.0-M3"

libraryDependencies ++=
  Seq(
    ("org.scala-js" %%% "scalajs-dom" % "1.1.0").withDottyCompat(scalaVersion.value),
    "org.scalameta" %% "munit"                 % "0.7.20" % Test,
    "org.scalameta" %% "munit-scalacheck"      % "0.7.20" % Test
  )

testFrameworks += new TestFramework("munit.Framework")

enablePlugins(ScalaJSPlugin)
scalaJSUseMainModuleInitializer := true

run / fork := true
