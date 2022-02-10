import sbt._
import sbt.Keys._
import mdoc.MdocPlugin

object WorksheetsRunner extends AutoPlugin {

  override def requires = super.requires && MdocPlugin
  override def trigger: PluginTrigger = NoTrigger
  object autoImport {
    lazy val checkWorksheets = taskKey[Unit]("Evaluates all worksheets")
  }
  import autoImport._
  import MdocPlugin.autoImport._

  override lazy val projectSettings = Seq(
    mdocIn := target.value / "worksheets_comp",
    checkWorksheetsDefinition
  )

  lazy val checkWorksheetsDefinition = checkWorksheets := Def.taskDyn {
    val worksheets =
      ((ThisBuild / baseDirectory).value / "" ** "*.worksheet.sc").get

    val dest = mdocIn.value
    if (dest.exists()) IO.delete(dest)
    IO.createDirectory(dest)

    val mdFiles: Seq[File] = worksheets.map { ws =>
      val rel: File = ws.relativeTo((ThisBuild / baseDirectory).value).map(_.getParentFile()).get
      dest / rel.getPath() / (ws.base.replace(".", "_") + ".md") 
    }

    IO.copy(worksheets.zip(mdFiles))

    mdFiles.foreach { f =>
      val content = IO.read(f)
      val newContent = s"```scala mdoc\n$content\n```"
      IO.write(f, newContent)
    }
    mdoc.toTask("")
  }.value

}
