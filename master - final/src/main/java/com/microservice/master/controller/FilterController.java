package com.microservice.master.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microservice.master.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class FilterController {

    @Autowired
    private Environment env;
    
    @Autowired
    private FilterService filterService;

    // Example URL : http://localhost:8080/microserviceE?customerId=5
    @PostMapping("/{microserviceName}")
    public ResponseEntity<ObjectNode> receiveFilterProperties(
            @PathVariable String microserviceName,
            @RequestParam int customerId,
            @RequestBody JsonNode payload
    ) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String includeFields = env.getProperty(microserviceName + ".customer" + customerId + ".filter-include-fields");
            if (includeFields != null && !includeFields.isEmpty()) {
                ObjectNode filteredNode = filterService.filterFields(payload, includeFields, objectMapper);
                return ResponseEntity.ok().body(filteredNode);
            }
            return ResponseEntity.ok().body(objectMapper.createObjectNode().put("message", "No filtering required"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
