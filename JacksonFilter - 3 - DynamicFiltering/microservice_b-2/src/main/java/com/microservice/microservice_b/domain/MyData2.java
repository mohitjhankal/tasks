package com.microservice.microservice_b.domain;

public class MyData2 {
    private String rollNo;
    private String grade;
    private String subject;
    private int postalCode;

    public MyData2() {
    }

    public MyData2(String rollNo, String grade, String subject, int postalCode) {
        this.rollNo = rollNo;
        this.grade = grade;
        this.subject = subject;
        this.postalCode = postalCode;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }
}
