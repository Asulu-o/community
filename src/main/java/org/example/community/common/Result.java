package org.example.community.common;

import lombok.Data;

@Data
public class Result<T> {

    private Integer code;     // 状态码：200成功，其他失败
    private String message;   // 提示信息
    private T data;           // 返回的数据

    // 构造方法私有，通过静态方法创建
    private Result() {}

    // 成功（无数据）
    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("成功");
        return result;
    }

    // 成功（有数据）
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage("成功");
        result.setData(data);
        return result;
    }

    // 成功（自定义消息）
    public static <T> Result<T> success(String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    // 失败
    public static <T> Result<T> error(String message) {
        Result<T> result = new Result<>();
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    // 失败（自定义状态码）
    public static <T> Result<T> error(Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}