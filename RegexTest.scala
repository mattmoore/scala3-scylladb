//> using scala "3.4.0"

@main
def main: Unit =
  val logLine = "INFO  2024-04-27 18:03:40,521 [shard  0:main] init - Scylla version 5.4.6-0.20240418.10f137e367e3 initialization completed."
  val version = "5.4.6"
  val regex = s"^.*Scylla version $version.*$$".r
  val regex2 = s"^.*Scylla version .* initialization complete.*$$".r
  val regex3 = "^.*Scylla version .* initialization complete.*$".r
  val result = regex3.findFirstMatchIn(logLine)
  println(result)
