package com.jim.itimerserver.entity.count;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SmokeCounter {
    private Long id;
    private String rev1;
    private String rev2;
    private String date;  // yyyyMMdd
    private String time;  // HHmmss
}
