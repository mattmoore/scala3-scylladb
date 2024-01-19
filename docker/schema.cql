CREATE KEYSPACE telemetry
WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

USE telemetry;

CREATE TABLE telemetry (id INT PRIMARY KEY, name TEXT);

INSERT INTO telemetry (id, name) VALUES (1, 'play');
INSERT INTO telemetry (id, name) VALUES (2, 'pause');
