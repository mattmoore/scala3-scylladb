CREATE KEYSPACE mutants
WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

USE mutants;

CREATE TABLE mutants (id INT PRIMARY KEY, name TEXT);

INSERT INTO mutants (id, name) VALUES (1, 'Godzilla');
INSERT INTO mutants (id, name) VALUES (2, 'Scylla');
