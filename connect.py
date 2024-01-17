from cassandra.cluster import Cluster

cluster = Cluster(['127.0.0.1'])

session = cluster.connect()
# Select from a table that is available without keyspace
res = session.execute('SELECT * FROM system.versions')
print(res.one())
