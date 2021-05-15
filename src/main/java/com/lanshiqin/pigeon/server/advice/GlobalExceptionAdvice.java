package com.lanshiqin.pigeon.server.advice;

import com.lanshiqin.pigeon.server.base.BaseResponse;
import com.lanshiqin.pigeon.server.constant.ResultCodeEnum;
import com.lanshiqin.pigeon.server.exception.BusinessException;
import com.lanshiqin.pigeon.server.exception.UnAuthException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author 蓝士钦
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    BaseResponse<String> exception(Exception exception) {
        logger.error("系统异常: {}"+ exception.getMessage(), exception);
        return BaseResponse.response(ResultCodeEnum.SYSTEM_EXCEPTION.getCode(), "系统异常", "${exception.message}");
    }

    @ExceptionHandler(BusinessException.class)
    BaseResponse<String> baseException(BusinessException exception) {
        logger.info("业务异常: {}"+ exception.getMessage(), exception);
        return BaseResponse.fail(exception.getMessage());
    }

    @ExceptionHandler(UnAuthException.class)
    BaseResponse<String> unAuthException(UnAuthException exception) {
        logger.info("未登录异常: {}", exception.getMessage());
        return BaseResponse.response(ResultCodeEnum.UN_AUTH.getCode(), exception.getMessage(), "");
    }
}
