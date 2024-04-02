package com.microservice.master.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

@Service
public class FilterService {

    public ObjectNode filterFields(JsonNode sourceNode, String includeFields, ObjectMapper objectMapper) {
        ObjectNode filteredNode = objectMapper.createObjectNode();
        String[] fieldsToInclude = includeFields.split(",");
        for (String fieldName : fieldsToInclude) {
            if (fieldName.contains(".")) {   // address.street
                String[] nestedFields = fieldName.split("\\.");   // [address, street]
                JsonNode nestedNode = navigateToNestedNode(sourceNode, nestedFields);  // "123 Main st"
                if (nestedNode != null) {
                    ObjectNode parentNode = filteredNode;
                    for (int i = 0; i < nestedFields.length - 1; i++) {
                        String currentField = nestedFields[i];  // address
                        if (!parentNode.has(currentField)) {   //
                            parentNode.putObject(currentField);  // { address : }
                        }
                        parentNode = (ObjectNode) parentNode.get(currentField);
                    }
                    parentNode.set(nestedFields[nestedFields.length - 1], nestedNode); //  [ "street", "123 Main st"]
                }
            } else if (fieldName.contains("[")) {
                String[] nestedFields = fieldName.split("\\[|\\]|&");
                JsonNode node = sourceNode.get(nestedFields[0]);
                if (node != null && node.isArray()) {
                    ArrayNode arr = objectMapper.createArrayNode();
                    for (JsonNode item : node) {
                        ObjectNode itemNode = objectMapper.createObjectNode();
                        for (int i = 1; i < nestedFields.length; i++) {
                            JsonNode fieldValue = item.get(nestedFields[i]);
                            if (fieldValue != null) {
                                itemNode.set(nestedFields[i], fieldValue);
                            } else {
                                return createErrorNode(fieldName);
                            }
                        }
                        arr.add(itemNode);
                    }
                    filteredNode.set(nestedFields[0], arr);
                } else {
                    return createErrorNode(nestedFields[0]);
                }
            } else {
                JsonNode fieldValue = sourceNode.get(fieldName);
                if (fieldValue != null) {
                    filteredNode.set(fieldName, fieldValue);
                } else {
                    return createErrorNode(fieldName);
                }
            }
        }
        return filteredNode;
    }

    public JsonNode navigateToNestedNode(JsonNode parentNode, String[] nestedFields) {
        JsonNode currentNode = parentNode;
        for (String field : nestedFields) {
            if (currentNode != null && currentNode.has(field)) {
                currentNode = currentNode.get(field);   // 1 - field -> address -> currentNode -> {"street":"123 Main St","city":"town","state":"CA","zip":"12345"}
                                                        // 2 - field -> street -> currentNode -> "123 Main st"
            } else {
                return null;
            }
        }
        return currentNode;
    }

    public ObjectNode createErrorNode(String fieldName) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode errorNode = objectMapper.createObjectNode();
        errorNode.put("error","Required field '" + fieldName + "' is missing in payload");
        return errorNode;
    }
}
