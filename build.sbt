ThisBuild / scalaVersion := "3.1.1"
lazy val root = (project in file("."))
  .aggregate(
    basics,
    domainModeling,
    collections,
    loops,
    modules,
    reasoningAboutCode.jvm,
    reasoningAboutCode.js,
    testing,
    typeDirectedProgramming,
    ExtensionMethodsAndImplicitConversions,
    ErrorHandling,
    asynchronousProgramming

)

lazy val basics =
  project.in(file("02_basics"))
    .enablePlugins(WorksheetsRunner)

lazy val domainModeling =
  project.in(file("03_domain_modeling"))
    .enablePlugins(WorksheetsRunner)

lazy val collections =
  project.in(file("04_collections"))
    .enablePlugins(WorksheetsRunner)

lazy val loops =
  project.in(file("05_loops"))
    .enablePlugins(WorksheetsRunner)

lazy val modules =
  project.in(file("08_modules"))
    .enablePlugins(WorksheetsRunner)

lazy val reasoningAboutCode =
  crossProject(JSPlatform, JVMPlatform)
    .in(file("09_reasoning_about_code"))
    .jsSettings(
      libraryDependencies ++=
        Seq(
          ("org.scala-js" %%% "scalajs-dom" % "1.1.0").cross(CrossVersion.for3Use2_13),
        )
    )
    .enablePlugins(WorksheetsRunner)

lazy val testing =
  project.in(file("10_testing"))
    .settings(
      libraryDependencies ++=
        Seq(
          "org.scalameta" %% "munit"            % "0.7.26" % Test,
          "org.scalameta" %% "munit-scalacheck" % "0.7.26" % Test
        )
    )
    .enablePlugins(WorksheetsRunner)

lazy val typeDirectedProgramming =
  project.in(file("11_type_directed_programming"))
    .enablePlugins(WorksheetsRunner)

lazy val ExtensionMethodsAndImplicitConversions =
  project.in(file("12_extension_methods_and_implicit_conversions"))
    .enablePlugins(WorksheetsRunner)

lazy val ErrorHandling =
  project.in(file("13_error_handling"))
    .enablePlugins(WorksheetsRunner)

lazy val asynchronousProgramming =
  project.in(file("14_asynchronous_programming"))
    .enablePlugins(WorksheetsRunner)
