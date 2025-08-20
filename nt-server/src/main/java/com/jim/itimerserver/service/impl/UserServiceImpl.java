package com.jim.itimerserver.service.impl;

import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.entity.common.RspCode;
import com.jim.itimerserver.entity.user.LoginVO;
import com.jim.itimerserver.entity.user.UserEntity;
import com.jim.itimerserver.mapper.UserMapper;
import com.jim.itimerserver.service.CaptchaService;
import com.jim.itimerserver.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CaptchaService captchaService;


    @Override
    public RestResponse create(UserEntity user) {
        int count = userMapper.countAll();
        if (count > 0) {
            return RestResponse.error(RspCode.USER_NOT_NULL);
        }

        int result = userMapper.insert(user);
        return RestResponse.success("用户创建成功:"+result);
    }

    @Override
    public RestResponse count() {
        int num = userMapper.countAll();
        return RestResponse.success(num);
    }

    @Override
    public RestResponse login(HttpServletRequest request,LoginVO vo) {
        boolean check_code = captchaService.verifyCaptcha(request,vo.getCode());
        if(!check_code) {
            return RestResponse.error(RspCode.USER_ERR_CODE);
        }
        return null;
    }

    @Override
    public RestResponse repwd(UserEntity user) {
        return null;
    }


}
