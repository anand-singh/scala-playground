import SbtUtil._

addCommandAlias("cd", "project")
addCommandAlias("root", "cd scala-playground")
addCommandAlias("c", "compile")
addCommandAlias("ca", "test:compile")
addCommandAlias("t", "test")
addCommandAlias("r", "run")
addCommandAlias("fmt", ";scalafmtSbt;scalafmt;test:scalafmt")
addCommandAlias("up2date", "reload plugins; dependencyUpdates; reload return; dependencyUpdates")

onLoadMessage +=
  s"""|
      |───────────────────────────
      |  List of defined ${styled("aliases")}
      |────────┬──────────────────
      |${styled("cd")}      │ project
      |${styled("root")}    │ cd scala-playground
      |${styled("c")}       │ compile
      |${styled("ca")}      │ compile all
      |${styled("t")}       │ test
      |${styled("r")}       │ run
      |${styled("fmt")}     │ scalafmt
      |${styled("up2date")} │ dependencyUpdates
      |────────┴──────────────────""".stripMargin
