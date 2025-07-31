package com.jim.itimerserver.controller;


import com.jim.itimerserver.utils.DateUtils;
import com.jim.itimerserver.utils.FileUtils;
import com.jim.itimerserver.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${timer.smoke_file_dir}")
    private String picDir;


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

}
