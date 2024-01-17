# ScyllaDB

Start ScyllaDB:

```shell
docker run --name scylla -d scylladb/scylla
```

Add nodes:

```shell
docker run --name scylla-node2 -d scylladb/scylla --seeds="$(docker inspect --format='{{ .NetworkSettings.IPAddress }}' scylla)"
docker run --name scylla-node3 -d scylladb/scylla --seeds="$(docker inspect --format='{{ .NetworkSettings.IPAddress }}' scylla)"
```

Check status:

```shell
docker exec -it scylla nodetool status
```

Cqlsh client connect:

```shell
docker exec -it scylla cqlsh
```
