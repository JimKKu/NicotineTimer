package com.jim.itimerserver.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {


    @PostMapping("/header")
    public String header(@RequestHeader HttpHeaders headers) {
        List<String> username = headers.get("username");
        List<String> password = headers.get("password");
        List<String> nono = headers.get("nono");
        username.forEach(item -> {
            System.out.println("username | " + item);
        });

        password.forEach(item -> {
            System.out.println("password | " + item);
        });

        return "饿了咯H";
    }

}
