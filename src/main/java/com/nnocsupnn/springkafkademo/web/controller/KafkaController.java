package com.nnocsupnn.springkafkademo.web.controller;

import com.nnocsupnn.springkafkademo.producer.KafkaProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaController {
    private final KafkaProducer producer;

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public String sendMessage(@RequestParam String message) {
        producer.sendMessage("test-topic", message);
        return "Message sent to Kafka!";
    }

    @PostMapping("/publish-avro")
    public String sendAvroMessage(@RequestParam String message) {
        producer.sendAvroMessage();
        return "Message sent to Kafka!";
    }
}
