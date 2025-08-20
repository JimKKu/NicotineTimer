package com.jim.itimerserver.service;

import com.jim.itimerserver.service.impl.CaptchaServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface CaptchaService {

    void genCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException;

    boolean verifyCaptcha(HttpServletRequest request, String inputCode);

}
