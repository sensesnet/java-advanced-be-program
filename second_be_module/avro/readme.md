## How-To-Run Confluent Cloud

- Create confluent.cloud account
- set up cluster
- set up topic and provide schema
- open schema-registry to find url & generate secrets
- open cluster settings to generate secret and cluster id
- run project with params
- send message


## How-To-Run Localy

### Install Confluent Platform

```
curl -O https://packages.confluent.io/archive/7.6/confluent-7.6.0.zip
```

```
unzip confluent-7.6.0.zip
```

### Configure CONFLUENT_HOME and PATH

Set the environment variable for the Confluent Platform home directory.

```
export CONFLUENT_HOME=<The directory where Confluent is installed>
```

Add the Confluent Platform bin directory to your PATH
```
export PATH=$PATH:$CONFLUENT_HOME/bin
```

Test that you set the CONFLUENT_HOME variable correctly by running the confluent command:
```
confluent --help
```
Your output should show the available commands for managing Confluent Platform.

### Running Confluent Platform locally for testing purposes

```
confluent local services start
```

Result
```
Using CONFLUENT_CURRENT: /var/folders/vh/mczd3tzn0knb2cbqpg0cr3pw0000gn/T/confluent.212462
ZooKeeper is [UP]
Kafka is [UP]
Starting Schema Registry
Schema Registry is [UP]
Starting Kafka REST
Kafka REST is [UP]
Starting Connect
Connect is [UP]
Starting ksqlDB Server
ksqlDB Server is [UP]
Starting Control Center
Control Center is [UP]
```

Confluent available on http://localhost:9021

Navigate to cluster settings and find [cluster_id; host; cluster_name]

Navigate to cluster topics and create topic: [message]

Navigate to topic schema and validate and save 

```
{
  "type": "record",
  "name": "AvroMessage",
  "namespace": "com.epam.learn",
  "fields": [
    {
      "name": "id",
      "type": "string"
    },
    {
      "name": "text",
      "type": "string"
    },
    {
      "name": "location",
      "type": "string"
    }
  ]
}
```

