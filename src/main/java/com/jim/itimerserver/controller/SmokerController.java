package com.jim.itimerserver.controller;

import com.jim.itimerserver.service.SmokeTimerService;
import com.jim.itimerserver.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/smoke")
public class SmokerController {


    @Autowired
    private SmokeTimerService service;

    @PostMapping("/mark")
    public String mark(@RequestHeader HttpHeaders headers) {
        if (!HttpUtils.checkUPHeader(headers)) {
            return "Mark Failed";
        }
        return service.mark();
    }

    @GetMapping("/count")
    public String get() {
        return service.countAll();
    }

}
