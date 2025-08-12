package com.jim.itimerserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jim.itimerserver.mapper")
public class ITimerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ITimerServerApplication.class, args);
    }

}
