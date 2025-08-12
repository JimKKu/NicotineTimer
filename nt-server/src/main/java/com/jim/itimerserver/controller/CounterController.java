package com.jim.itimerserver.controller;

import com.jim.itimerserver.entity.count.DailyCount;
import com.jim.itimerserver.service.CounterService;
import com.jim.itimerserver.utils.DateUtils;
import com.jim.itimerserver.utils.FileUtils;
import com.jim.itimerserver.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/smoke")
public class CounterController {


    @Autowired
    private CounterService countService;

    @PostMapping("/mark")
    public String mark(@RequestHeader HttpHeaders headers) {
        if (!HttpUtils.checkUPHeader(headers)) {
            return "Mark Failed";
        }
        return countService.mark();
    }

    @GetMapping("/count")
    public String get() {
        return countService.countAll();
    }

    @GetMapping("/chart/{startDate}/{endDate}")
    public List<DailyCount> chart(@RequestHeader HttpHeaders headers, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        return countService.getChartData(startDate,endDate);
    }

}
