package com.jim.itimerserver.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Primary;

import java.io.Serializable;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private Integer id;
    private String username;
    private String password;
    private String remark;
    private String createTime;
    private String updateTime;
}
