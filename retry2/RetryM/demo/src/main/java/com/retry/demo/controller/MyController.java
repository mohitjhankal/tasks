package com.retry.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/api/info")
    public String getInfo(){
        return callApi("http://localhost:8901/example/api");
    }

    public String callApi(String api) {
        int maxRetries = 4;
        int retryCount = 0;
        RuntimeException execption = null;

        while (retryCount < maxRetries){
            try {
                ResponseEntity<String> response = restTemplate.getForEntity(api, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    return response.getBody();
                } else {
                    throw new RuntimeException("API call failed with status code: " + response.getStatusCodeValue());
                }
            } catch (RuntimeException e) {
                execption = e;
                retryCount++;
                try {
                    Thread.sleep(3000); // 3000 milliseconds = 3 seconds
                    System.out.println("tryin.....");
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw execption;
    }
}
