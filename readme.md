# Springboot InfluxDB 2.0

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

## Endpoints 
* Add Entry: `curl localhost:8080/audit -X POST -d '{"name": "tommi"}' -H 'Content-Type: application/json' `
* List all Entries: `curl localhost:8080/audit | jq '.[0].records' `