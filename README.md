# k8s-service
This project is a demo project for k8s spring boot using cassandra, redis and rabbitmq skeleton. 

to use this example 

git clone ....k8s-service.git

mvn package docker:build      --> builds a docker container



# Make sure to pull the following images 

docker pull redis:latest

docker pull rabbitmq:3-management

docker pull cassandra:latest



# Using minikube 


cd  k8s

kubectl create -f redis/

kubectl create -f rabbitmq/

kubectl create -f cassandra/


docker ps and find the cassandra hash

docker run --link <cassandraHash>:cassandra -it --rm cassandra cqlsh cassandra

for example : docker run --link b2bfb42c9de2:cassandra  -it    --rm cassandra cqlsh cassandra


run the following cql commands:

CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE mykeyspace; 

CREATE TABLE user (id text PRIMARY KEY, name text, email text, age int);

CREATE INDEX user_name_index ON user (name);

exit 


kubectl create -f k8s-service/



POST http://192.168.99.100:31804/user

Accept:application/json

Content-Type:application/json

Body 

{
   "id":"1",
   "name":"spieler",
   "email":"spieler.orasio@gmail.com",
   "age":33
}

now try http://192.168.99.100:31804/user/id/1


# Using DOCKER 
 

docker run --name latestRedis -d -it  -p 6379:6379 redis:latest

(to check connectivity you can use Redis Desktop manager 192.168.99.100 6379)

docker run -d -it  -p 5672:5672   -p 15672:15672 --hostname latestRabbit --name latestRabbit \

 -e RABBITMQ_ERLANG_COOKIE='ABCDABCD'  -e RABBITMQ_DEFAULT_USER=orasio \
 
 -e RABBITMQ_DEFAULT_PASS=password  rabbitmq:3-management
 

(to check connectivity you can connect http://192.168.99.100:15672/    orasio/password)


docker run --name latestCassandra -d -it -p 9042:9042 cassandra:latest

run cql commands

docker run --link latestCassandra:cassandra  -it    --rm cassandra cqlsh cassandra


CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

USE mykeyspace;

CREATE TABLE user (id text PRIMARY KEY, name text, email text, age int);

CREATE INDEX user_name_index ON user (name);


docker run -p 9988:9988 -e "REDIS_ADDRESS=192.168.99.100" -e "RABBITMQ_ADDRESS=192.168.99.100" -e "CASSANDRA_ADDRESS=192.168.99.100" -d -it spieler/k8s-service



Open rest tool and execute 

POST http://localhost:9988/user

Accept:application/json

Content-Type:application/json

Body
{
   "id":"1",
   "name":"spieler",
   "email":"spieler.orasio@gmail.com",
   "age":33
}

now test the data

GET http://localhost:9988/user/email/spieler.orasio@gmail.com

GET http://localhost:9988/user/name/spieler

GET http://localhost:9988/user/id/1






   
# Debugging the application in a Docker container

docker run   -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n"  -e "REDIS_ADDRESS=192.168.99.100" -e "RABBITMQ_ADDRESS=192.168.99.100" -e "CASSANDRA_ADDRESS=192.168.99.100" -p 9988:9988 -p 5005:5005  -d -it spieler/k8s-service



# Spring Boot Actuator is on 
try http://192.168.99.100:9988/docs/

for example http://192.168.99.100:9988/actuator


# Spring Cloud Config is on too (Later an example will be added)


# take down the system 

kubectl delete -f k8s-service/

kubectl delete -f redis/

kubectl delete -f cassandra/

kubectl delete -f rabbitmq/



