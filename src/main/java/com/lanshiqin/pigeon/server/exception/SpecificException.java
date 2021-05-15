package com.lanshiqin.pigeon.server.exception;


import com.lanshiqin.pigeon.server.constant.ResultCodeEnum;

/**
 * 特定异常
 *
 * @author 蓝士钦
 */
public class SpecificException extends RuntimeException {

    private Integer resultCode;

    public SpecificException(final ResultCodeEnum resultCode, final String resultMsg) {
        super(resultMsg);
        this.resultCode = resultCode.getCode();
    }

    public Integer getResultCode() {
        return resultCode;
    }
}
