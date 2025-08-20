package com.jim.itimerserver.entity.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RspCode {
    SUCCESS(200, "操作成功"),
    NULL(199,"数据为空"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(400, "参数校验失败"),
    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "资源不存在"),
    INTERNAL_SERVER_ERROR(500, "服务器内部错误"),
    USER_NOT_NULL(900,"创建失败，用户已存在"),
    USER_ERR_CODE(901,"验证码错误");

    private final int code;
    private final String msg;
}
