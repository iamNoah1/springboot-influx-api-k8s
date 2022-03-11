# Springboot InfluxDB 2.0
Super simple example of how to use InfluxDB 2 with Spring Boot. 

## Prerequisites
* Java SDK 11
* Docker installed

## Run locally 
* `docker run -d -p 8086:8086 \
      -v $PWD/data:/var/lib/influxdb2 \
      -v $PWD/config:/etc/influxdb2 \
      -e DOCKER_INFLUXDB_INIT_MODE=setup \
      -e DOCKER_INFLUXDB_INIT_USERNAME=my-user \
      -e DOCKER_INFLUXDB_INIT_PASSWORD=my-password \
      -e DOCKER_INFLUXDB_INIT_ORG=my-org \
      -e DOCKER_INFLUXDB_INIT_BUCKET=my-bucket \
      -e DOCKER_INFLUXDB_INIT_RETENTION=1w \
      -e DOCKER_INFLUXDB_INIT_ADMIN_TOKEN=my-super-secret-auth-token \
      influxdb:2.0`
* `./mvnw clean spring-boot:run`

## Run in the cloud (k8s)
* `cp src/main/resources/application-cloud.properties.example src/main/resources/application-cloud.properties` and fill the values. 
* set `SPRING_PROFILES_ACTIVE` to `cloud`
* `./mvnw clean install`
* `docker build -t <registry>/spring-influx .`
* `docker push <registry>/spring-influx`
* `cp k8s-manifest.yaml.example k8s-manifest.yaml` and adjust the image containter registry
* `kubectl apply -f k8s-manifest.yaml`
* `kubectl get svc influx-spring` to find out the loadbalancer IP

## Endpoints 
* Add Entry: `curl localhost:8080/audit -X POST -d '{"name": "tommi"}' -H 'Content-Type: application/json' `
* List all Entries: `curl localhost:8080/audit | jq '.[0].records' `
* Replace `localhost:8080` with the IP from the loadbalancer if running in the cloud (k8s)

## Resources 
* https://github.com/influxdata/influxdb-java
* https://hub.docker.com/_/influxdb
