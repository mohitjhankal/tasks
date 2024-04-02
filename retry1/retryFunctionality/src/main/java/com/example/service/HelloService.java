package com.example.service;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HelloService {

    private final RestTemplate restTemplate;
    private int attemptCount = 0;
    private static final int MAX_ATTEMPTS = 5; // Maximum number of retry attempts
   

    public HelloService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Retryable(maxAttempts = MAX_ATTEMPTS,  backoff = @Backoff(delay = 3000))
    public String getHelloWithRetry() {
        String url = "http://localhost:8081/test"; // URL of the Hello Microservice
        System.out.println("Attempt " + attemptCount + " passed");
        attemptCount++;
        // Perform HTTP request and return response
        return restTemplate.getForObject(url, String.class);
    }

}

