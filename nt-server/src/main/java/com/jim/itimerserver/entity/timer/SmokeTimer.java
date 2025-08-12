package com.jim.itimerserver.entity.timer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SmokeTimer {
    private Integer id;             // 主键ID
    private String datetime;       // 日期时间(YYYYMMDDHHmm)
    private String createTime;     // 创建时间(YYYYMMDDHHMMSS)
}
