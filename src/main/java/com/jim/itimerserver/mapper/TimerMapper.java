package com.jim.itimerserver.mapper;


import com.jim.itimerserver.entity.timer.SmokeTimer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TimerMapper {

    @Insert("INSERT INTO DATE_INFO(datetime, create_time) VALUES(#{datetime}, #{createTime})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(SmokeTimer dateInfo);
    

    @Select("SELECT * FROM DATE_INFO WHERE id=#{id}")
    SmokeTimer selectById(Integer id);

    @Select("SELECT * FROM DATE_INFO ORDER BY create_time DESC")
    List<SmokeTimer> selectAll();

    @Select("SELECT * FROM DATE_INFO ORDER BY id DESC LIMIT 1")
    SmokeTimer getLatest();

}
