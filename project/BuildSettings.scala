import sbt._
import sbt.Keys._
import scala.Array
import sbtbuildinfo.Plugin._

object BuildSettings {

  import BuildDependencies._

  val gitHubDeps = gitHubDependencies.getOrElse(Array.empty[ClasspathDep[ProjectReference]])

  def systemSpecsFilter(name: String): Boolean = name endsWith "SysSpec"

  def integrationSpecsFilter(name: String): Boolean = name endsWith "IntSpec"

  def allSpecsFilter(name: String): Boolean = name endsWith "Spec"

  def databaseDependentSpecsFilter(name: String): Boolean = systemSpecsFilter(name) || integrationSpecsFilter(name)

  def databaseIndependentSpecsFilter(name: String): Boolean = !systemSpecsFilter(name) && !integrationSpecsFilter(name)

  //test is unit
  //int is integration tests
  //all is unit :: int :: sys
  //sys is end to end system tests


  lazy val IntTests = config("int") extend (Test)
  lazy val AllTests = config("all") extend (Test)
  lazy val SysTests = config("sys") extend (Test)

  lazy val ourSettings = Seq[Project.Setting[_]](
    organization := App.organization,
    resolvers ++= Seq(
      "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/"
    ),
//    testListeners += SbtTapReporting(),
    testOptions in Test := Seq(
      Tests.Setup {
        () =>
          System.setProperty("config.file", "conf/test.conf")
          System.setProperty("http.port", "19007")
      },
      Tests.Filter(s => databaseIndependentSpecsFilter(s))),
    testOptions in IntTests := Seq(
      Tests.Setup {
        () =>
          System.setProperty("config.file", "conf/test.conf")
          System.setProperty("http.port", "19007")
      },
      Tests.Filter(s => integrationSpecsFilter(s))),
    testOptions in SysTests := Seq(
      Tests.Setup {
        () =>
          System.setProperty("config.file", "conf/test.conf")
          System.setProperty("http.port", "19007")
      },
      Tests.Filter(s => systemSpecsFilter(s))),
    testOptions in AllTests := Seq(
      Tests.Setup {
        () => System.setProperty("config.file", "conf/test.conf")
          System.setProperty("http.port", "19007")
      },
      Tests.Filter(s => allSpecsFilter(s))
    ),
    fork in Test := false,
    fork in IntTests := false,
    fork in SysTests := false,
    fork in AllTests := false,
    parallelExecution in Test := false,
    parallelExecution in IntTests := false,
    parallelExecution in SysTests := false,
    parallelExecution in AllTests := false,
    javaOptions in Runtime += "-Dconfig.file=conf/test.conf"
  )
  //https://github.com/sbt/sbt-buildinfo
  val ourBuildInfoSettings = buildInfoSettings ++ Seq(
    sourceGenerators in Compile <+= buildInfo,
    buildInfoKeys := Seq[BuildInfoKey](name, version, scalaVersion,organization),
    buildInfoPackage := App.appName
  )
}
