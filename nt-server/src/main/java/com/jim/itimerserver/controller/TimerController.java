package com.jim.itimerserver.controller;


import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.service.CounterService;
import com.jim.itimerserver.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/timer")
public class TimerController {

    @Autowired
    private TimerService service;

    @GetMapping("/set/{date}")
    public RestResponse set(@PathVariable("date") String date) {
        return service.insert(date);
    }

    @GetMapping("/latest")
    public RestResponse getLatest() {
         return service.getLatest();
    }

}
