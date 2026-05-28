package org.example.community.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理自定义业务异常
    @ExceptionHandler(BusinessException.class)
    public Result<?> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    // 处理所有未捕获的异常
    @ExceptionHandler(Exception.class)
    public Result<?> handleException(Exception e) {
        e.printStackTrace();  // 打印日志，方便调试
        return Result.error("系统内部错误：" + e.getMessage());
    }

    // 处理空指针异常
    @ExceptionHandler(NullPointerException.class)
    public Result<?> handleNullPointerException(NullPointerException e) {
        return Result.error("空指针异常：" + e.getMessage());
    }

    // 处理参数校验异常（后续添加@Valid时使用）
    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public Result<?> handleValidationException(
            org.springframework.web.bind.MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Result.error(400, message);
    }
}