package com.jim.itimerserver.controller;

import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.service.UserService;
import com.jim.itimerserver.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
public class WebController {

    @Autowired
    private WebService webService;

    @GetMapping("/init")
    public RestResponse init() {
        return webService.initInfo();
    }

}
