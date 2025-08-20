package com.jim.itimerserver.service.impl;

import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.entity.user.UserEntity;
import com.jim.itimerserver.mapper.TimerMapper;
import com.jim.itimerserver.mapper.UserMapper;
import com.jim.itimerserver.service.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebServiceImpl implements WebService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    TimerMapper timerMapper;


    /**
     * 返回内容：
     * 1. 都未初始化：3
     * 2. 用户已初始化，时间未初始化：2
     * 3. 用户未初始化，时间已初始化：1
     * 4. 都已初始化：0
     */
    @Override
    public RestResponse initInfo() {
        int users = userMapper.countAll();
        int times = timerMapper.countAll();
        // only 0 or 1
        if(times >= 1){ times = 1; }
        if(users >= 1){ times = 1; }

        String str = 3 - users - times*2 + "";
        return RestResponse.success(str);
    }
}
