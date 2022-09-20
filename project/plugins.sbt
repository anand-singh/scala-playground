ThisBuild / scalaVersion    := "2.12.15"
ThisBuild / useSuperShell   := false
ThisBuild / autoStartServer := false

update / evictionWarningOptions := EvictionWarningOptions.empty

addSbtPlugin("ch.epfl.scala"    % "sbt-scalafix"    % "0.10.2")
addSbtPlugin("com.timushev.sbt" % "sbt-rewarn"      % "0.1.3")
addSbtPlugin("com.timushev.sbt" % "sbt-updates"     % "0.6.1")
addSbtPlugin("com.typesafe.sbt" % "sbt-git"         % "1.0.2")
addSbtPlugin("io.spray"         % "sbt-revolver"    % "0.9.1")
addSbtPlugin("org.scalameta"    % "sbt-scalafmt"    % "2.4.6")
addSbtPlugin("org.wartremover"  % "sbt-wartremover" % "2.4.16")
