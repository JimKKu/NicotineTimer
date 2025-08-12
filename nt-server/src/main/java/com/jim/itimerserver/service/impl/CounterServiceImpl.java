package com.jim.itimerserver.service.impl;

import com.jim.itimerserver.entity.count.DailyCount;
import com.jim.itimerserver.entity.count.SmokeCounter;
import com.jim.itimerserver.mapper.CounterMapper;
import com.jim.itimerserver.service.CounterService;
import com.jim.itimerserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CounterServiceImpl implements CounterService {

    @Autowired
    private CounterMapper mapper;

    @Override
    public String mark() {
        // 计算日期 - 时间
        String datetime = DateUtils.getCurrentTime();
        String time = datetime.substring(8,datetime.length());
        String date = datetime.substring(0,8);

        // 对象赋值
        SmokeCounter smokeCounter = SmokeCounter.builder()
                .date(date)
                .time(time)
                .build();

        // 入表
        int res = mapper.insert(smokeCounter);
        if(res != 1) {
            throw new RuntimeException("新增失败");
        }
        return String.valueOf(mapper.countAll());
    }

    public String countAll() {
        return String.valueOf(mapper.countAll());
    }

    @Override
    public List<DailyCount> getChartData(String startDate, String endDate) {
        return mapper.countDailyData(startDate, endDate);
    }




}
