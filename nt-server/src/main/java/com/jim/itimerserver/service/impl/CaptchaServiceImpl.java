package com.jim.itimerserver.service.impl;

import com.jim.itimerserver.service.CaptchaService;

import com.jim.itimerserver.utils.CaptchaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    // 验证码长度
    private static final int CODE_LENGTH = 4;
    // session中存储验证码的key
    private static final String SESSION_KEY = "captcha_code";


    @Override
    public void genCaptcha(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        // 设置响应头
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 生成验证码
        String code = CaptchaUtil.generateRandomCode(CODE_LENGTH);

        // 将验证码存入session
        HttpSession session = request.getSession();
        session.setAttribute(SESSION_KEY, code);

        // 生成图片
        byte[] captchaImage = CaptchaUtil.generateCaptchaImage(code);

        // 输出图片
        try (OutputStream out = response.getOutputStream()) {
            out.write(captchaImage);
            out.flush();
        }
    }

    @Override
    public boolean verifyCaptcha(HttpServletRequest request,
                                String inputCode) {
        HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute(SESSION_KEY);

        if (sessionCode == null) {
            throw new RuntimeException("验证码已过期");
        }

        if (sessionCode.equalsIgnoreCase(inputCode)) {
            // 验证成功后移除session中的验证码
            session.removeAttribute(SESSION_KEY);
            return true;
        } else {
            return false;
        }
    }
}
