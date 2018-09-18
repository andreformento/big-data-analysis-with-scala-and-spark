# Kafka Spark streaming

_If you want to use an IDE to run examples, maybe you need see in your IDE how to `include dependency with 'provided' scope`_

### Run

Execute:

```shell
sbt run
# and choose number of KafkaStreamingApp class
```

### Kafka

Before, configure your hosts: `sudo sh -c "sudo echo '127.0.0.1 kafka' >> /etc/hosts"`

Docker:

```shell
docker-compose up
```

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

- https://spark.apache.org/docs/2.2.0/structured-streaming-programming-guide.html
- https://spark.apache.org/docs/2.2.0/structured-streaming-kafka-integration.html
