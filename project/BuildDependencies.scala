import sbt._
import sbt.Keys._
import play.Play.autoImport._
import play.core.PlayVersion

object BuildDependencies extends DepUtils {

  import BuildVersions._

  val gitHubDependencies: Option[Array[ClasspathDep[ProjectReference]]] = None
  //Array(RootProject(uri("https://github.com/nmccready/akka-fileio.git")))

  lazy val resolutionRepos = Seq(
    "Sonatype Releases" at "https://oss.sonatype.org/content/repositories/releases/",
    "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/",
    "sonatype-public" at "https://oss.sonatype.org/content/repositories/public")

  lazy val compileDeps = Seq(
    /*"com.github.scala-incubator.io" %% "scala-io-core" % scalax_io, //scala-io-file is in play*/
    "org.mockito" % "mockito-core" % mockito,
    "com.typesafe.akka" %% "akka-contrib" % akka,
    "org.scalikejdbc" %% "scalikejdbc-async" % scalalikejdbc_async,
    "com.github.mauricio" %% "postgresql-async" % postgresql_async
  )

  lazy val testDeps = Seq(
    component("play") % "test",
    component("play-test") % "test",
    component("play-cache") % "test",
    /*"com.github.scala-incubator.io" %% "scala-io-core" % scalax_io,*/
    "com.typesafe.akka" %% "akka-testkit" % akka,
    "org.scalatest" %% "scalatest" % scalaTest,
    "org.mockito" % "mockito-core" % mockito
    /*"com.chuusai" %% "shapeless" % shapeless*/
    // "de.vorb" %% "akka-fileio" % akka_fileio
  )

  lazy val appDependencies = compileDeps ++ testDeps
}

trait DepUtils {
  def addScalaV(namespace: String, version: String): String = appendAll(namespace, "_", version)

  def appendAll(strings: String*) = {
    val sb = new StringBuilder()
    strings.foreach(s => sb.append(s))
    sb.toString
  }
}
