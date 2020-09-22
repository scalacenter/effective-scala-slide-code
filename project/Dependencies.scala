import sbt._
import dotty.tools.sbtplugin.DottyPlugin.autoImport.DottyCompatModuleID

object Version {
  val logbackVer        = "1.2.3"
  val mUnitVer          = "0.7.12"
  val scalaVersion      = "0.27.0-RC1"
  val doodleVersion     = "0.9.21"
  val scalajsdomVersion = "1.1.0"
}

object Dependencies {

  private val logbackDeps = Seq (
    "ch.qos.logback"                 %  "logback-classic",
  ).map (_ % Version.logbackVer)

  private val munitDeps = Seq(
    "org.scalameta" %% "munit" % Version.mUnitVer % Test,
    "org.scalameta" %% "munit-scalacheck" % Version.mUnitVer % Test
  )

  private val scalajsDeps: Seq[ModuleID] = Seq(
    "org.scala-js" % "scalajs-dom_sjs1_2.13" % Version.scalajsdomVersion
  )

  private val doodleDeps: Seq[ModuleID] = Seq(
    "org.creativescala" %% "doodle" % Version.doodleVersion,
  )

  val dependencies: Seq[ModuleID] =
    logbackDeps ++
    munitDeps //++
    //scalajsDeps
    
  val crossDependencies: Seq[ModuleID] =
    doodleDeps 
}
