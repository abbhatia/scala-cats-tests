lazy val root = (project in file(".")).settings(
  inThisBuild(
    List(
      organization := "com.example",
      scalaVersion := "2.13.3"
    )
  ),
  name := "scala-cats-tests"
)

resolvers ++= Seq(
  "Sonatype Snapshots".at("https://oss.jfrog.org/artifactory/oss-snapshot-local")
)

libraryDependencies ++=
  Seq(
    "org.typelevel" %% "cats-core" % "2.1.1",
    "org.typelevel" %% "cats-effect" % "2.1.2",
    "ch.qos.logback" % "logback-classic" % "1.2.3",
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
  ) ++
    Seq("org.scalatest" %% "scalatest" % "3.0.8" % Test) ++
    Seq(
      compilerPlugin("org.typelevel" %% "kind-projector" % "0.11.0").cross(CrossVersion.full)
    )

scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:higherKinds",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard"
) ++ (CrossVersion.partialVersion(scalaVersion.value) match {
  case Some((2, n)) if n >= 13 => Seq("-Xsource:3")
  case Some((2, n)) if n < 13 => Seq("-Ypartial-unification")
  case _ => Seq()
})
