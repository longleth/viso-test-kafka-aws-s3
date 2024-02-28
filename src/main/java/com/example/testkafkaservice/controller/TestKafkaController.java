package com.example.testkafkaservice.controller;

import com.example.testkafkaservice.data.TestTopic;
import com.example.testkafkaservice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@RequestMapping("/api/v1")
public class TestKafkaController {

    private final String awsS3UploadPartUri =
            "https://{Bucket-name}.s3.amazonaws.com/{Key+}?partNumber={PartNumber}&uploadId={UploadId}";
    private final String clientRegistrationId = "outbound-oauth2-client";

    private TestService testService;
    private WebClient webClient;

    @PostMapping(value = "/test-kafka")
    public void testKafka(TestTopic testTopic) {

        testService.publishResult(testTopic);
    }

    @PostMapping(value = "/test-aws-s3")
    public void testAwsS3(
            @RegisteredOAuth2AuthorizedClient(clientRegistrationId)
            OAuth2AuthorizedClient authorizedClient) {

        webClient.post()
                .uri(awsS3UploadPartUri)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .toEntity(Object.class)
                .block();
    }

    @Autowired
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @Autowired
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

}
