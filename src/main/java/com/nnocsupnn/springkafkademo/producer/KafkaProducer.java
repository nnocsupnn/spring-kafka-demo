package com.nnocsupnn.springkafkademo.producer;

import com.nnocsupnn.User;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public void sendAvroMessage() {
        Properties props = new Properties();
        props.setProperty("acks", "1");
        props.setProperty("retries", "10");

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", "http://127.0.0.1:8082");

        String topic = "user-topic";
        org.apache.kafka.clients.producer.KafkaProducer<String, User> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, User>(props);
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
