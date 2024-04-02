package com.microservice.master.domain;

import com.fasterxml.jackson.annotation.JsonFilter;

import java.util.List;

@JsonFilter("myData")
public class MyData {
    private String id;
    private String name;
    private String email;
    private int age;
    private List<String> languages;

    public MyData() {
    }

    public MyData(String id, String name, String email, int age, List<String> languages) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.languages = languages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }
}
