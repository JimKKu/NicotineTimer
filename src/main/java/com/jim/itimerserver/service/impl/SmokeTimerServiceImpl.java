package com.jim.itimerserver.service.impl;

import com.jim.itimerserver.entity.model.DailyCount;
import com.jim.itimerserver.entity.timer.SmokeTimer;
import com.jim.itimerserver.mapper.SmokeTimerMapper;
import com.jim.itimerserver.service.SmokeTimerService;
import com.jim.itimerserver.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

@Service
public class SmokeTimerServiceImpl implements SmokeTimerService {

    @Autowired
    private SmokeTimerMapper mapper;

    @Override
    public String mark() {
        // 计算日期 - 时间
        String datetime = DateUtils.getCurrentTime();
        String time = datetime.substring(8,datetime.length());
        String date = datetime.substring(0,8);

        // 对象赋值
        SmokeTimer smokeTimer = SmokeTimer.builder()
                .date(date)
                .time(time)
                .build();

        // 入表
        int res = mapper.insert(smokeTimer);
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
        List<DailyCount> dailyCounts = mapper.countDailyData(startDate, endDate);
        System.out.println(dailyCounts.size());
        dailyCounts.forEach(dailyCount -> {
            System.out.println("---------" + dailyCount.getDate() + " | " + dailyCount.getCount());
        });
        return dailyCounts;
    }

}
