# Kafka Spark streaming

_If you want to use an IDE to run examples, maybe you need see in your IDE how to `include dependency with 'provided' scope`_

### Run

Execute:

```shell
sbt run
# and choose number of KafkaStreamingApp class
```

### Kafka (local)

```shell
wget http://mirror.nbtelecom.com.br/apache/kafka/2.0.0/kafka_2.11-2.0.0.tgz -P kafka-install
cd kafka-install
tar -xzf kafka_2.11-2.0.0.tgz
cd kafka_2.11-2.0.0
```

Quick-and-dirty single-node ZooKeeper instance: `bin/zookeeper-server-start.sh config/zookeeper.properties`

Now start the Kafka server (in new terminal): `bin/kafka-server-start.sh config/server.properties`

Create a topic named "topic1" (in new terminal)
```shell
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic1
```

Show
```shell
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

Produce a message
```shell
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic1 --property parse.key=true --property key.separator="|"
```

Consume a message
```shell
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic1 --from-beginning
```

### Kafka (compose)

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
- https://kafka.apache.org/quickstart
