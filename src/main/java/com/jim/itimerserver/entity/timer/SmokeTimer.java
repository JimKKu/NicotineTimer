package com.jim.itimerserver.entity.timer;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SmokeTimer {
    private Long id;
    private String rev1;
    private String rev2;
    private String date;  // yyyyMMdd
    private String time;  // HHmmss
}
