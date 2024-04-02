package com.microservice.master.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("microservice.dynamic-field-mapping")
public class DynamicFieldMappingProperties {
    private String defaultFields;

    public String getDefaultFields() {
        return defaultFields;
    }

    public void setDefaultFields(String defaultFields) {
        this.defaultFields = defaultFields;
    }
}
