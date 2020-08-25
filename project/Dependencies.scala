import sbt._
import dotty.tools.sbtplugin.DottyPlugin.autoImport.DottyCompatModuleID

object Version {
  val logbackVer        = "1.2.3"
  val mUnitVer          = "0.7.11"
  val scalaVersion      = "0.26.0"
  val doodleVersion     = "0.9.21"
}

object Dependencies {

  private val logbackDeps = Seq (
    "ch.qos.logback"                 %  "logback-classic",
  ).map (_ % Version.logbackVer)

  private val munitDeps = Seq(
    "org.scalameta" %% "munit" % Version.mUnitVer % Test
  )
  
  private val doodleDeps: Seq[ModuleID] = Seq(
    "org.creativescala" %% "doodle" % Version.doodleVersion,
  )

  val dependencies: Seq[ModuleID] =
    logbackDeps ++
    munitDeps
    
  val crossDependencies: Seq[ModuleID] =
    doodleDeps 
}
