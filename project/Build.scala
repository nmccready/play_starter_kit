import sbt._
import sbt.Keys._
import play.Play.autoImport._
import PlayKeys._
import trafficland.opensource.sbt.plugins._
import org.sbtidea.SbtIdeaPlugin._

object App{
  val appName = "play_starter_kit"
  val appVersion = "0.0.1"
  val organization = "nmccready"
}

object ApplicationBuild extends Build {
  import App._
  import BuildDependencies._
  import BuildSettings._

  val main = Project(appName, file("."))
  .enablePlugins(play.PlayScala)
  .settings(
    version := appVersion,
    libraryDependencies ++= appDependencies)
  .configs(IntTests, AllTests, SysTests)
  .settings(ideaExcludeFolders := ".idea" :: ".idea_modules" :: Nil)
  .settings(inConfig(IntTests)(Defaults.testTasks): _ *)
  .settings(inConfig(AllTests)(Defaults.testTasks): _ *)
  .settings(inConfig(SysTests)(Defaults.testTasks): _ *)
  .settings(ourSettings: _*)
  /*.settings(Play20.plug : _*)*/
  .settings(ourBuildInfoSettings: _*)
  .settings(net.virtualvoid.sbt.graph.Plugin.graphSettings: _*)
  .dependsOn(gitHubDeps: _*)
}
