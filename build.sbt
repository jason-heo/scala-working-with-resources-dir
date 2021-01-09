
name := "scala-working-with-resources-dir"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.github.pureconfig" %% "pureconfig" % "0.9.1",
  "com.typesafe" % "config" % "1.3.0"
)

assemblyJarName in assembly := "example.jar"

Compile / unmanagedResourceDirectories += baseDirectory.value / "conf"
