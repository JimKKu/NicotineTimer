package com.jim.itimerserver.service;

import com.jim.itimerserver.entity.count.DailyCount;

import java.util.List;

public interface CounterService {

    /** 标记一次 */
    String mark();

    /** 获取所有次数 */
    String countAll();

    /** 获取柱状图信息 | 横坐标与竖坐标 */
    List<DailyCount> getChartData(String startDate, String endDate);

}
