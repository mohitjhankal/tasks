package com.microservice.microservice_a.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.microservice.microservice_a.domain.MyData;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sendFilterProperties")
public class FilterPropertiesSenderController {

    @PostMapping
    public ResponseEntity<MyData> sendFilterProperties(@RequestBody JsonNode payload){
        try {
            MyData myData = createMyDataFromPayload(payload);
            return ResponseEntity.ok().body(myData);
        }catch (Exception e) {
            System.out.println("In Exception");
            throw new RuntimeException(e);
        }
    }

    private MyData createMyDataFromPayload(JsonNode payload){
        MyData myData = new MyData();
        myData.setId(payload.get("id").asText());
        myData.setName(payload.get("name").asText());
        myData.setAge(payload.get("age").asInt());
        myData.setEmail(payload.get("email").asText());
        myData.setLanguages(parseLanguages(payload.get("languages")));
        return myData;
    }
    private List<String> parseLanguages(JsonNode languagesNode) {
        List<String> languages = new ArrayList<>();
        if (languagesNode.isArray()) {
            for (JsonNode language : languagesNode) {
                languages.add(language.asText());
            }
        }
        return languages;
    }
}
