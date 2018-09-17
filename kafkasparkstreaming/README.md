# Kafka Spark streaming

_If you want to use an IDE to run examples, maybe you need see in your IDE how to `include dependency with 'provided' scope`_

### Run

Execute:

```shell
sbt run
# and choose number of CountingLocalApp class
```

### Kafka

Docker:

```shell
docker-compose up
```

Show Kafka Manager: http://localhost:9000

Create a kafka topic
```shell
docker-compose exec kafka /opt/kafka/bin/kafka-topics.sh \
                          --create \
                          --zookeeper zookeeper:2181 \
                          --replication-factor 1 \
                          --partitions 1 \
                          --topic topic1
```

List Kafka topics
```shell
docker-compose exec kafka /opt/kafka/bin/kafka-topics.sh --list --zookeeper zookeeper:2181
```

Produce messages
```shell
docker-compose exec kafka /opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic1
```

Consume messages
```shell
docker-compose exec kafka /opt/kafka/bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic1 --from-beginning
```

### Reference

- https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html
- https://github.com/SciSpike/kafka-lab/blob/master/labs/01-Verify-Installation/hello-world-kafka.md
