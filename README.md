# Conduktor - Produce & Consume Protobuf Messages to Kafka topic

This blog talks about 

1. Create Kafka Topic and Protobuf Schema from Conduktor UI.
2. Producer Protobuf Messages to Topic using Java Producer.
3. Consume Protobuf messages from Conduktor.

## Create Kafka Topic & Attach Proto Schema ->

Once you have configured and connected your cluster in Conduktor, You can create a topic from Conduktor UI by choosing `Topics` from left and then clicking on `CREATE` on top right. Check below screenshot where we are creating topic `testprotobuftopic` with 6 partitions and 3 replication factor.

![alt text](https://github.com/rahulgulati89/Kafka/blob/main/Images/CreateKafkaTopic.png)

Once topic is created, it looks like below.

![alt text](https://github.com/rahulgulati89/Kafka/blob/main/Images/KafkaTopic.png)

Next you need to create a protobuf schema and attach it to the above created topic. To create schema, choose `Schema Registry` from left and click on `CREATE` on top right corner. For creating schema, you need to choose below mentioned options.

`Format` -> `Protobuf`
`Strategy` -> `Topic Name`
`Key or Value` -> `Value`
`Topic` -> `Choose topic from drop down`

Our protobuf schema looks like below and we are going to attach it to topic `testprotobuftopic` created in above step.

```
syntax = "proto3";
package conduktor.v1;

option java_outer_classname = "ProductProtobuf";

message Product {
    int32 product_id = 1;
    string name = 2;
    string description = 3;
}
```

![alt text](https://github.com/rahulgulati89/Kafka/blob/main/Images/ProtobufSchema1.png)

Once your schema is created, it will be shown up in Schema Registry page with name as `$topicname-value`. This is because we have choosen Strategy as `topic name`.

![alt text](https://github.com/rahulgulati89/Kafka/blob/main/Images/ProtobufSchema2.png)

## Producer Protobuf Messages to Topic using Java Producer

For this step, we are going to use Java Producer in maven based project. The producer will produce messages with key as String and value serialized with Protobuf Serializer. You can look at the code in this repository for the same Java protobuf Producer. Note that we are using `protoc-jar-maven-plugin`  plugin in our maven pom.xml. This is required to generate protobuf code bindings from our `Product.proto` file so that we can use those code bindigs to produce messages with Product type. You just need to compile this maven project('mvn clean' and 'mvn complie') and the code bindings will be generate automatically from this plugin.

## Consume Protobuf messages from Conduktor UI

Once protobuf messages are produced to the topic, we can consume those from Conduktor UI. To do this, you need to Choose Topics from the left pane and click on your topic from which you want to consume data. For this demo, we are going to consume data from our `testprotobuftopic` topic to which We have produced the messages in step 2. Once you are on your topic, Click on `Consume Data` on the top right corner.

![alt text](https://github.com/rahulgulati89/Kafka/blob/main/Images/Consumer1.png)


Choose below mentioned settings from Conduktor UI to start consuming data from the topic.

Under `Format` choose `key` as `String` and `Value` as `Protobuf(Schema Registry)`.
Under `Parameters` choose `Start from` as `the beginning(earliest)`.

![alt text](https://github.com/rahulgulati89/Kafka/blob/main/Images/Consumer2.png)

After choosing all the above settings, Click `Start` from bottom left and you will see protobuf messages which have been produced to this topic in step 2. If you dont see any messages the check your settings again or check your producer.

![alt text](https://github.com/rahulgulati89/Kafka/blob/main/Images/Consumer3.png)
