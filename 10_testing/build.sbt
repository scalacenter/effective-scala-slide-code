scalaVersion := "3.1.1"
libraryDependencies ++=
  Seq(
    "org.scalameta" %% "munit"            % "0.7.26" % Test,
    "org.scalameta" %% "munit-scalacheck" % "0.7.26" % Test
  )