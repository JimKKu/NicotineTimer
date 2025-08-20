package com.jim.itimerserver.entity.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private String username;
    private String password;
    private String code;
}
