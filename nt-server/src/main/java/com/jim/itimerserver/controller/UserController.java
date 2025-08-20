package com.jim.itimerserver.controller;


import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.entity.user.LoginVO;
import com.jim.itimerserver.entity.user.UserEntity;
import com.jim.itimerserver.service.CaptchaService;
import com.jim.itimerserver.service.UserService;
import com.jim.itimerserver.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/count")
    public RestResponse count() {
        return userService.count();
    }

    @PostMapping("/new")
    public RestResponse create(@RequestBody UserEntity user) {
        return userService.create(user);
    }

    @PostMapping("/repwd")
    public RestResponse repwd(@RequestBody UserEntity user) {
        return userService.repwd(user);
    }

    @PostMapping("/login")
    public RestResponse login(HttpServletRequest request,
                              @RequestBody LoginVO vo
    ) {
        return userService.login(request,vo);
    }


    @GetMapping("/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        captchaService.genCaptcha(request, response);
    }



}
