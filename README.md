# Kafka Testing

A container image for starting up Apache Kafka and ZooKeeper in a single container,
for testing purposes.

## Build

```shell script
./mvnw clean verify

docker build -f src/main/docker/Dockerfile.jvm-cds -t debezium/kafka-testing .

docker run -i --rm -p 9093:9093 debezium/kafka-testing
```

The build produces an App CDS archive which allows for a faster start-up of the broker.

## License

This code base is licensed under the Apache License, version 2.
