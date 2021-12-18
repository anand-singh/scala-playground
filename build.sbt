ThisBuild / organization := "playground"
ThisBuild / scalaVersion := "2.13.7"

lazy val `scala-playground` =
  project
    .in(file("."))
    .settings(name := "scala-playground")
    .settings(commonSettings: _*)
    .settings(dependencies: _*)

lazy val commonSettings = {
  import Dependencies._
  Seq(
    addCompilerPlugin(com.olegpy.`better-monadic-for`),
    addCompilerPlugin(org.augustjune.`context-applied`),
    addCompilerPlugin(org.typelevel.`kind-projector`),
    update / evictionWarningOptions := EvictionWarningOptions.empty,
    Compile / console / scalacOptions := {
      (Compile / console / scalacOptions)
        .value
        .filterNot(_.contains("wartremover"))
        .filterNot(Scalac.Lint.toSet)
        .filterNot(Scalac.FatalWarnings.toSet) :+ "-Wconf:any:silent"
    },
    Test / console / scalacOptions := (Compile / console / scalacOptions).value,
  )
}

lazy val dependencies = {
  import Dependencies._

  Seq(
    libraryDependencies ++= Seq(
      org.typelevel.catsCore,
      io.scalaland.chimney,
      io.scalaland.chimneyCats,
    ),
    libraryDependencies ++= Seq(
      com.github.alexarchambault.`scalacheck-shapeless_1.14`,
      org.scalacheck.scalacheck,
      org.scalatest.scalatest,
      org.scalatestplus.`scalacheck-1-14`,
      org.typelevel.`discipline-scalatest`,
    ).map(_ % Test),
  )
}
