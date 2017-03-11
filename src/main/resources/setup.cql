CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
USE mykeyspace;
CREATE TABLE user (id text PRIMARY KEY, name text, email text, age int);
CREATE INDEX user_name_index ON user (name);
