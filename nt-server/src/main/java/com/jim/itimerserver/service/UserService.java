package com.jim.itimerserver.service;

import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.entity.user.LoginVO;
import com.jim.itimerserver.entity.user.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserService {

    RestResponse create(UserEntity user);

    RestResponse count();

    RestResponse login(HttpServletRequest request,LoginVO vo);

    RestResponse repwd(UserEntity user);
}
