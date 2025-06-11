package com.jim.itimerserver.mapper;

import com.jim.itimerserver.entity.model.DailyCount;
import com.jim.itimerserver.entity.timer.SmokeTimer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface SmokeTimerMapper {

    int insert(SmokeTimer record);

    int countAll();

    List<SmokeTimer> selectByDate(@Param("date") String date);

    List<DailyCount> countDailyData(@Param("startDate") String startDate, @Param("endDate") String endDate);


}
