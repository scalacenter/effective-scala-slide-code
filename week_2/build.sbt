scalaVersion := "3.0.0-M3"
libraryDependencies += ("org.creativescala" %% "doodle" % "0.9.21").withDottyCompat(scalaVersion.value)
Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / useSuperShell := false
