lazy val week_1 = (project in file("week_1")).enablePlugins(WorksheetsRunner)
lazy val week_2 = (project in file("week_2")).enablePlugins(WorksheetsRunner)
lazy val week_3 = (project in file("week_3")).enablePlugins(WorksheetsRunner)
lazy val week_4 = (project in file("week_4")).enablePlugins(WorksheetsRunner)
lazy val week_5 = (project in file("week_5")).enablePlugins(WorksheetsRunner)
lazy val week_6 = (project in file("week_6")).enablePlugins(WorksheetsRunner)

lazy val root = (project in file("."))
  .aggregate(
    week_1,
    week_2,
    week_3,
    week_4,
    week_5,
    week_6
)
