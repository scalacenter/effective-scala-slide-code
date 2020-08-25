import sbt.Keys._
import sbt._
import sbtstudent.AdditionalSettings
import dotty.tools.sbtplugin.DottyPlugin.autoImport.DottyCompatModuleID

object CommonSettings {
  lazy val commonSettings = Seq(
    Compile / scalacOptions ++= CompileOptions.compileOptions,
    libraryDependencies ++= Dependencies.dependencies,
    // libraryDependencies ++= Dependencies.crossDependencies.map(_.withDottyCompat(scalaVersion.value)),
  )

  lazy val configure: Project => Project = (project: Project) => {
    project.settings(CommonSettings.commonSettings: _*)
  }
}
