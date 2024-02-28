package com.example.testkafkaservice.service;

import com.example.testkafkaservice.data.TestTopic;

public interface TestService {

    byte[] shoot(String pageUrl);
    void saveImageToS3(byte[] image);
    void publishResult(TestTopic message);

}
