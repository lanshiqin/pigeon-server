package com.lanshiqin.pigeon.server.exception;

import com.lanshiqin.pigeon.server.constant.ResultCodeEnum;

/**
 * 未登录异常
 *
 * @author 蓝士钦
 */
public class UnAuthException extends RuntimeException {

    private Integer resultCode;

    public UnAuthException(String resultMsg) {
        super(resultMsg);
        this.resultCode = ResultCodeEnum.UN_AUTH.getCode();
    }

    public Integer getResultCode() {
        return resultCode;
    }
}
