# k8s-service
k8s spring boot cassandra redis rabbitmq skeleton 






mvn package docker:build



docker run --name latestRedis -d -it  -p 6379:6379 redis:latest
Redis Desktop manager 192.168.99.100 6379

docker run -d -it  -p 5672:5672   -p 15672:15672 --hostname latestRabbit --name latestRabbit \
 -e RABBITMQ_ERLANG_COOKIE='ABCDABCD'  -e RABBITMQ_DEFAULT_USER=orasio \
 -e RABBITMQ_DEFAULT_PASS=password  rabbitmq:3-management
http://192.168.99.100:15672/    orasio/password


docker run --name latestCassandra -d -it -p 9042:9042 cassandra:latest

#run cql commands
docker run --link latestCassandra:cassandra  -it    --rm cassandra cqlsh cassandra


CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
USE mykeyspace;
CREATE TABLE user (id text PRIMARY KEY, name text, email text, age int);
CREATE INDEX user_name_index ON user (name);


Open rest tool
POST http://localhost:9988/user
Accept:application/json
Content-Type:application/json
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

mvn package docker:build



######### DOCKER    ##############

docker run -p 9988:9988 -e "REDIS_ADDRESS=192.168.99.100" -e "RABBITMQ_ADDRESS=192.168.99.100" -e "CASSANDRA_ADDRESS=192.168.99.100" -d -it spieler/k8s-service
   
## Debugging the application in a Docker container
docker run   -e "JAVA_OPTS=-agentlib:jdwp=transport=dt_socket,address=5005,server=y,suspend=n"  -e "REDIS_ADDRESS=192.168.99.100" -e "RABBITMQ_ADDRESS=192.168.99.100" -e "CASSANDRA_ADDRESS=192.168.99.100" -p 9988:9988 -p 5005:5005  -d -it spieler/k8s-service



### actuator is on 
try http://192.168.99.100:9988/docs/
for example http://192.168.99.100:9988/actuator



############  k8s ##############

 # Start a single instance of hazelcast and let the container expose port 6379 .
kubectl run  redis --port=6379 --image=redis:latest --replicas=1


cd .../k8s-service
kubectl create -f rabbitmq/
kubectl delete -f rabbitmq/
kubectl describe service rabbitmq-service
kubectl describe pod rabbitmq-deployment-1944598380-d8asp
kubectl describe deployment rabbitmq-deployment
go to http://192.168.99.100:31802/    orasio/password


kubectl create -f redis/
kubectl delete -f redis/

# kubectl describe deployment redis-deployment
kubectl describe service redis-service

open redisDesktop and connect to 192.168.99.100:31800 




kubectl create -f cassandra/
kubectl delete -f cassandra/
kubectl describe service cassandra-service
you can connect to cassandra vi 192.168.99.100:31803

now to run some commands against cassandra 
run docker ps and find the uuid for example
docker run --link 2a088e848f04:cassandra   -it    --rm cassandra cqlsh cassandra



CREATE KEYSPACE mykeyspace WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };
USE mykeyspace;
CREATE TABLE user (id text PRIMARY KEY, name text, email text, age int);
CREATE INDEX user_name_index ON user (name);



kubectl create -f k8s-service/
kubectl delete -f k8s-service/
trouble shoot error => kubectl describe pod k8s-service-deployment-776000777-1w6b6

go to http://192.168.99.100:31804/     

Remove dangling images 
docker images -f dangling=true
docker rmi $(docker images -f dangling=true -q)


C:\dev\k8s-service\k8s\k8s-service


cd k8s\redis
kubectl create -f deployment.yaml
kubectl create -f service.yaml
kubectl describe deployment redis-deployment
kubectl get services
kubectl describe service redis-service
kubectl get pods
kubectl describe pod redis-deployment-1210358476-6qbbr


cd k8s\cassandra
kubectl create -f deployment.yaml
kubectl create -f service.yaml


cd k8s\rabbitmq
kubectl create -f config.yaml
kubectl create -f deployment.yaml
kubectl create -f service.yaml
kubectl describe service rabbitmq-service
kubectl delete deployment rabbitmq-deployment
kubectl delete service rabbitmq-service
kubectl expose service  rabbitmq-service --port=15672 --target-port=15672

kubectl port-forward rabbitmq-deployment-2977575605-9nbxa      15672:31740
Trouble shoot by kubectl describe pods rabbitmq-deployment-468595494-bjxq1

test
kubectl port-forward rabbitmq-deployment-2667395629-t86za      15672:32533



kubectl  run k8s-service --image=spieler/k8s-service:latest --replicas=2 --port=9988 --env="REDIS_ADDRESS=192.168.99.100"  



