import sbt._
import sbt.Keys._

import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._
import dotty.tools.sbtplugin.DottyPlugin.autoImport._

//object MyScalaJSPlugin extends AutoPlugin {
//
//  override def requires: Plugins = ScalaJSPlugin
//
//  override def projectSettings: Seq[Setting[_]] = Def.settings(
//    /* Remove the Scala.js compiler plugin for scalac, and enable the
//     * Scala.js back-end of dotty instead.
//     */
//    libraryDependencies := {
//      val deps = libraryDependencies.value
//      deps.filterNot(_.name.startsWith("scalajs-compiler")).map(_.withDottyCompat(scalaVersion.value))
//    },
//    scalacOptions += "-scalajs",
//
//    // Replace the JVM JUnit dependency by the Scala.js one
//    libraryDependencies ~= {
//      _.filter(!_.name.startsWith("junit-interface"))
//    },
//    libraryDependencies ++= Seq(
//      ("org.scala-js" %% "scalajs-junit-test-runtime" % scalaJSVersion  % "test").withDottyCompat(scalaVersion.value),
//      "ch.epfl.lamp" % "dotty-library-bootstrappedjs_sjs1_0.27" % "0.27.0-bin-SNAPSHOT"
//    ),
//
//    // Typecheck the Scala.js IR found on the classpath
//    scalaJSLinkerConfig ~= (_.withCheckIR(true))
//  )
//}
