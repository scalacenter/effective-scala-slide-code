scalaVersion := "3.3.0"
libraryDependencies ++=
  Seq(
    "org.scalameta" %% "munit"            % "1.2.0" % Test,
    "org.scalameta" %% "munit-scalacheck" % "1.2.0" % Test
  )