scalaVersion := "3.3.1"
libraryDependencies ++=
  Seq(
    "org.scalameta" %% "munit"            % "0.7.29" % Test,
    "org.scalameta" %% "munit-scalacheck" % "0.7.29" % Test
  )