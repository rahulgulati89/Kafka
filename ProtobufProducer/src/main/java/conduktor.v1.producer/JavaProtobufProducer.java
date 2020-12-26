
package conduktor.v1.producer;

import conduktor.v1.ProductProtobuf;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializer;
import io.confluent.kafka.serializers.protobuf.KafkaProtobufSerializerConfig;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Instant;
import java.time.Period;
import java.util.Properties;

public class JavaProtobufProducer {

    public static void main(String[] args) {

        Properties properties = new Properties();


        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaProtobufSerializer.class);
        properties.setProperty("bootstrap.servers", "saxovml0034.sys.dom:9092,saxovml0026.sys.dom:9092,saxovml0037.sys.dom:9092");
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "10");
        properties.setProperty("schema.registry.url", "http://saxovml0037.sys.dom:8081");
        properties.setProperty("sasl.kerberos.service.name", "kafka");
        properties.setProperty("security.protocol","SASL_SSL");

        Producer<String, ProductProtobuf.Product> producer = new KafkaProducer<String, ProductProtobuf.Product>(properties);

        String topic = "testprotobuftopic";
        String key = "product_id";

        ProductProtobuf.Product product = ProductProtobuf.Product.newBuilder().setProductId(123).setName("Conduktor").setDescription("Conduktor Testing").build();

        ProducerRecord<String, ProductProtobuf.Product> record
                = new ProducerRecord<String, ProductProtobuf.Product>(topic, key, product);
        System.out.println(record);
        producer.send(record);
        producer.flush();
        producer.close();

    }
}
