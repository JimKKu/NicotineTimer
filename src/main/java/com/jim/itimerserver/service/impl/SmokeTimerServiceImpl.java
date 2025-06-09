package com.jim.itimerserver.service.impl;

import com.jim.itimerserver.entity.timer.SmokeTimer;
import com.jim.itimerserver.mapper.SmokeTimerMapper;
import com.jim.itimerserver.service.SmokeTimerService;
import com.jim.itimerserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SmokeTimerServiceImpl implements SmokeTimerService {

    @Autowired
    private SmokeTimerMapper mapper;

    @Override
    public String mark() {
        SmokeTimer smokeTimer = new SmokeTimer();
        smokeTimer.setDate(DateUtils.getCurrentDay());
        smokeTimer.setTime(DateUtils.getCurrentTime());
        int res = mapper.insert(smokeTimer);
        if(res != 1) {
            throw new RuntimeException("新增失败");
        }
        return String.valueOf(mapper.countAll());
    }
}
