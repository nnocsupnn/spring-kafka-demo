package com.nnocsupnn;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

import java.util.Properties;

public class AvroKafkaProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty("acks", "1");
        props.setProperty("retries", "10");

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://127.0.0.1:8082");

        String topic = "user-topic";
        KafkaProducer<String, User> producer = new KafkaProducer<String, User>(props);
        User userx = User.newBuilder()
                .setId("1")
                .setName("Nino")
                .setEmail("test@email.com")
                .build();

        ProducerRecord<String, User> record = new ProducerRecord<>(topic, userx);
        producer.send(record, (recordMetadata, e) -> {
            if (e == null) {
                System.out.println("Success!");
                System.out.println(recordMetadata.toString());
            } else {
                System.out.println("error-shit!!");
                e.printStackTrace();
            }
        });

        producer.flush();
        producer.close();
    }
}
