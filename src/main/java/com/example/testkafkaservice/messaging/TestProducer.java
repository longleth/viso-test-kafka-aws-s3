package com.example.testkafkaservice.messaging;

import com.example.testkafkaservice.data.TestTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class TestProducer {

    private KafkaTemplate<String, TestTopic> kafkaTemplate;

    @Autowired
    public TestProducer(KafkaTemplate<String, TestTopic> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TestTopic testTopic) {
        kafkaTemplate.send("test_topic", testTopic);
    }

}
