package com.jim.itimerserver.service;


import com.jim.itimerserver.entity.common.RestResponse;
import com.jim.itimerserver.entity.timer.SmokeTimer;

import java.util.Timer;

public interface TimerService {

    RestResponse insert(String datetime);

    RestResponse getLatest();
}
