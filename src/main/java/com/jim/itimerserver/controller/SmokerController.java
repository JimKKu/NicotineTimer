package com.jim.itimerserver.controller;

import com.jim.itimerserver.entity.model.DailyCount;
import com.jim.itimerserver.service.SmokeTimerService;
import com.jim.itimerserver.utils.DateUtils;
import com.jim.itimerserver.utils.FileUtils;
import com.jim.itimerserver.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/smoke")
public class SmokerController {

    @Value("${timer.file_dir}")
    private String picDir;


    @Autowired
    private SmokeTimerService service;

    @PostMapping("/mark")
    public String mark(@RequestHeader HttpHeaders headers) {
        if (!HttpUtils.checkUPHeader(headers)) {
            return "Mark Failed";
        }
        return service.mark();
    }

    @GetMapping("/count")
    public String get() {
        return service.countAll();
    }

    @PostMapping("/pic")
    public String pic(@RequestParam MultipartFile file,@RequestHeader HttpHeaders headers) throws IOException {

        // 验证请求头
        if (!HttpUtils.checkUPHeader(headers)) {
            return "Mark Failed";
        }

        // 获取路径信息
        String today = DateUtils.getCurrentDay();
        log.info("{} | 上传一张图片", today);
        String type = HttpUtils.getHeaderValue(headers,"type");
        String originalFilename = file.getOriginalFilename();
        String suffix = FileUtils.getFileExtension(originalFilename);
        String filePath = picDir + type + "/" + today + suffix;

        // 存储图片
        FileUtils.save(file, filePath);
        return "OK";
    }

    @GetMapping("/chart/{startDate}/{endDate}")
    public List<DailyCount> chart(@RequestHeader HttpHeaders headers, @PathVariable("startDate") String startDate, @PathVariable("endDate") String endDate) {
        return service.getChartData(startDate,endDate);
    }

}
