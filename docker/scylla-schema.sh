export SCYLLA_CONTAINER_ID="$(docker inspect -f '{{.Id}}' scylla)"
docker cp docker/schema.cql $SCYLLA_CONTAINER_ID:./schema.cql
docker exec -it scylla cqlsh -f /schema.cql
