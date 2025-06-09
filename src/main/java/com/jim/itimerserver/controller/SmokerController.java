package com.jim.itimerserver.controller;

import com.jim.itimerserver.service.SmokeTimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smoke")
public class SmokerController {


    @Autowired
    private SmokeTimerService service;

    @PostMapping("/mark")
    public String mark() {
        return service.mark();
    }

}
