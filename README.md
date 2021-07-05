# Kafka Testing

A container image for starting up Apache Kafka and ZooKeeper in a single container,
for testing purposes.

## Build

```shell script
./mvnw clean verify
docker build -f src/main/docker/Dockerfile.jvm -t debezium/kafka-testing .
docker run -i --rm -p 9093:9093 debezium/kafka-testing
```

## License

This code base is licensed under the Apache License, version 2.
