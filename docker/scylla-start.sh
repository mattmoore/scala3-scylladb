docker run --rm -it \
    --name scylla \
    -p 127.0.0.1:9042:9042 \
    -d scylladb/scylla \
    --smp 1 \
    --listen-address 0.0.0.0 \
    --broadcast-rpc-address 127.0.0.1
