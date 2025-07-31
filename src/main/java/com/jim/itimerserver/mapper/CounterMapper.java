package com.jim.itimerserver.mapper;

import com.jim.itimerserver.entity.count.DailyCount;
import com.jim.itimerserver.entity.count.SmokeCounter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CounterMapper {

    int insert(SmokeCounter record);

    int countAll();

    List<SmokeCounter> selectByDate(@Param("date") String date);

    List<DailyCount> countDailyData(@Param("startDate") String startDate, @Param("endDate") String endDate);


}
