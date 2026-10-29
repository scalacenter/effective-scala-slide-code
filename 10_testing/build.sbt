scalaVersion := "3.3.9"
libraryDependencies ++=
  Seq(
    "org.scalameta" %% "munit"            % "0.7.29" % Test,
    "org.scalameta" %% "munit-scalacheck" % "0.7.29" % Test
  )