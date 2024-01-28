package containers

import com.dimafeng.testcontainers.GenericContainer
import com.dimafeng.testcontainers.GenericContainer.DockerImage
import containers.ScyllaDbContainer.ScyllaDbPort
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy

import scala.concurrent.duration.*
import scala.jdk.CollectionConverters.*
import scala.jdk.DurationConverters.*

class ScyllaDbContainer private (
  hostPort: Int,
  underlying: GenericContainer
) extends GenericContainer(underlying) {
  underlying.container.setPortBindings(
    List(s"$hostPort:$ScyllaDbPort").asJava
  )
}

object ScyllaDbContainer {
  private val ScyllaDbPort = 9042

  private val container = GenericContainer(
    dockerImage = "scylladb/scylla",
    exposedPorts = Seq(ScyllaDbPort),
    waitStrategy = new LogMessageWaitStrategy()
      .withRegEx(
        "^.*Scylla version 5.4.1-0.20231231.3d22f42cf9c3 initialization completed.*$"
      )
      .withStartupTimeout(2.minutes.toJava)
  )

  case class Def(hostPort: Int)
    extends GenericContainer.Def[ScyllaDbContainer](new ScyllaDbContainer(hostPort, container))
}
