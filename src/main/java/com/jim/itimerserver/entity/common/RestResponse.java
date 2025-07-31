package com.jim.itimerserver.entity.common;
import lombok.Data;
import java.io.Serializable;

@Data
public class RestResponse<T> implements Serializable {
    private int code;       // 状态码
    private String msg;     // 返回消息
    private T data;         // 返回数据

    // 成功返回（无数据）
    public static <T> RestResponse<T> success() {
        return success(null);
    }

    // 成功返回（带数据）
    public static <T> RestResponse<T> success(T data) {
        RestResponse<T> result = new RestResponse<>();
        result.setCode(RspCode.SUCCESS.getCode());
        result.setMsg(RspCode.SUCCESS.getMsg());
        result.setData(data);
        return result;
    }

    // 失败返回
    public static <T> RestResponse<T> error(int code, String msg) {
        return error(code, msg, null);
    }

    // 失败返回（带数据）
    public static <T> RestResponse<T> error(int code, String msg, T data) {
        RestResponse<T> result = new RestResponse<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    // 使用枚举的错误返回
    public static <T> RestResponse<T> error(RspCode resultCode) {
        return error(resultCode.getCode(), resultCode.getMsg());
    }
}