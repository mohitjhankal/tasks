package com.microservice.microservice_b.controller;

import com.microservice.microservice_b.domain.MyData2;
import com.microservice.microservice_b.domain.Payload;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/sendFilterProperties")
@RestController
public class FilterPropertiesController {

    @PostMapping
    public ResponseEntity<MyData2> sendFilterProperties(@RequestBody Payload payload){
        try {
            MyData2 myData = createMyDataFromPayload(payload);
            return ResponseEntity.ok().body(myData);
        }catch (Exception e) {
            System.out.println("In Exception");
            throw new RuntimeException(e);
        }
    }
    private MyData2 createMyDataFromPayload(Payload payload){
        MyData2 myData2 = new MyData2();
        myData2.setRollNo(payload.getRollNo());
        myData2.setGrade(payload.getGrade());
        myData2.setSubject(payload.getSubject());
        myData2.setPostalCode(payload.getPostalCode());
        return myData2;
    }
}
