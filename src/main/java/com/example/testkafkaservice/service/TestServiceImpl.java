package com.example.testkafkaservice.service;

import com.example.testkafkaservice.data.TestTopic;
import com.example.testkafkaservice.messaging.TestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private TestProducer testProducer;

    /**
     * @param pageUrl
     * @return
     */
    @Override
    public byte[] shoot(String pageUrl) {
        return new byte[0];
    }

    /**
     * @param image
     */
    @Override
    public void saveImageToS3(byte[] image) {

    }

    /**
     * @param message
     */
    @Override
    public void publishResult(TestTopic message) {
        testProducer.sendMessage(message);
    }

    @Autowired
    public void setTestProducer(TestProducer testProducer) {
        this.testProducer = testProducer;
    }
}
