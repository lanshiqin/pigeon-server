package com.lanshiqin.pigeon.server.base;


import com.lanshiqin.pigeon.server.constant.ResultCodeEnum;

/**
 * 基础返回体
 *
 * @param <T> 返回数据类型
 * @author 蓝士钦
 */
public class BaseResponse<T> {
    /**
     * 返回码
     */
    private Integer resultCode;
    /**
     * 返回信息
     */
    private String resultMsg;
    /**
     * 返回数据
     */
    private T data;

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(final Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(final String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    /**
     * 响应结果
     *
     * @param resultCode 返回码
     * @param message    返回信息
     * @param data       返回数据
     * @param <T>        返回数据类型
     * @return 响应数据
     */
    public static <T> BaseResponse<T> response(final Integer resultCode, final String message, final T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setResultCode(resultCode);
        response.setResultMsg(message);
        response.setData(data);
        return response;
    }

    /**
     * 操作成功
     *
     * @param message 成功信息
     * @return 操作成功信息对象
     */
    public static BaseResponse<String> success(final String message) {
        return response(ResultCodeEnum.SUCCESS.getCode(), message, null);
    }

    /**
     * 操作成功
     *
     * @param message 成功信息
     * @param data    成功内容
     * @return 操作成功信息和内容对象
     */
    public static <T> BaseResponse<T> success(final String message, final T data) {
        return response(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 操作成功
     *
     * @param data    成功内容
     * @return 操作成功信息和内容对象
     */
    public static <T> BaseResponse<T> success(final T data) {
        return response(ResultCodeEnum.SUCCESS.getCode(), "操作成功", data);
    }

    /**
     * 列表查询返回结果
     *
     * @param message 成功信息
     * @param data    结果集对象
     * @param <T>     结果集对象类型
     * @return 操作成功信息，和结果集对象
     */
    public static <T> BaseResponse<ListResult<T>> list(final String message, final ListResult<T> data) {
        return response(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 操作失败
     *
     * @param message 操作失败信息
     * @return 操作失败信息对象
     */
    public static <T> BaseResponse<T> fail(final String message) {
        return response(ResultCodeEnum.BUSINESS_EXCEPTION.getCode(), message, null);
    }

    /**
     * 操作失败
     *
     * @param message 操作失败信息
     * @param data    操作失败数据
     * @param <T>     操作失败数据类型
     * @return 操作失败信息对象
     */
    public static <T> BaseResponse<T> fail(final String message, final T data) {
        return response(ResultCodeEnum.BUSINESS_EXCEPTION.getCode(), message, data);
    }

    /**
     * 系统错误
     *
     * @param message 系统错误信息
     * @param data    系统错误数据
     * @param <T>     操系统错误数据类型
     * @return 系统错误信息对象
     */
    public static <T> BaseResponse<T> error(final String message, final T data) {
        return response(ResultCodeEnum.SYSTEM_EXCEPTION.getCode(), message, data);
    }
}
