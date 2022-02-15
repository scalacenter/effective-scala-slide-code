lazy val root = (project in file("."))
  .aggregate(
    basics,
    domainModeling,
    collections,
    loops,
    modules,
    testing,
    typeDirectedProgramming,
    extensionMethodsAndImplicitConversions,
    errorHandling,
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

lazy val testing =
  project.in(file("10_testing"))
    .enablePlugins(WorksheetsRunner)

lazy val typeDirectedProgramming =
  project.in(file("11_type_directed_programming"))
    .enablePlugins(WorksheetsRunner)

lazy val extensionMethodsAndImplicitConversions =
  project.in(file("12_extension_methods_and_implicit_conversions"))
    .enablePlugins(WorksheetsRunner)

lazy val errorHandling =
  project.in(file("13_error_handling"))
    .enablePlugins(WorksheetsRunner)

lazy val asynchronousProgramming =
  project.in(file("14_asynchronous_programming"))
    .enablePlugins(WorksheetsRunner)
