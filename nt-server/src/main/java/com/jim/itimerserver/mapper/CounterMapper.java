package com.jim.itimerserver.mapper;

import com.jim.itimerserver.entity.count.DailyCount;
import com.jim.itimerserver.entity.count.SmokeCounter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CounterMapper {

    @Insert("INSERT INTO smoke_timer (rev1, rev2, date, time)" +
            "VALUES (#{rev1}, #{rev2}, #{date}, #{time})")
    int insert(SmokeCounter record);

    @Select("SELECT COUNT(1)" +
            "FROM smoke_timer")
    int countAll();

    @Select("SELECT * FROM smoke_timer WHERE date = #{date}")
    List<SmokeCounter> selectByDate(@Param("date") String date);

    @Select("SELECT date, COUNT(*) AS count" +
            "FROM smoke_timer" +
            "WHERE date BETWEEN #{startDate} AND #{endDate}" +
            "GROUP BY date" +
            "ORDER BY date ASC")
    List<DailyCount> countDailyData(@Param("startDate") String startDate, @Param("endDate") String endDate);


}
