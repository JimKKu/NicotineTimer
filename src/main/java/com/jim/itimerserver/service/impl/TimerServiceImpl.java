package com.jim.itimerserver.service.impl;

import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.entity.common.RspCode;
import com.jim.itimerserver.entity.timer.SmokeTimer;
import com.jim.itimerserver.mapper.TimerMapper;
import com.jim.itimerserver.service.TimerService;
import com.jim.itimerserver.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TimerServiceImpl implements TimerService {

    @Autowired
    private TimerMapper mapper;


    @Override
    public RestResponse insert(String datetime) {

        SmokeTimer timer = SmokeTimer.builder()
                .datetime(datetime)
                .createTime(DateUtils.getCurrentTime())
                .build();

        int res = mapper.insert(timer);

        if(res > 0){
            return RestResponse.success();
        }
        return RestResponse.error(RspCode.FAILED);
    }

    @Override
    public RestResponse getLatest() {
        SmokeTimer timer = mapper.getLatest();
        if (timer == null) {
            return RestResponse.error(RspCode.NULL);
        }

        return RestResponse.success(timer);
    }
}
