package com.nnocsupnn.springkafkademo.consumer;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "group1", info = "listen1")
    public void listen1(String message) {
        System.out.println("Consumer 1: " + message);
    }


    /**
     * Avro Message Listener
     * @param message
     *
     * @topic is your avro topic
     */
    @KafkaListener(topics = "user-topic", groupId = "group1")
    public void listen2(GenericRecord message) {
        System.out.println("Avro Message: " + message);
    }
}
