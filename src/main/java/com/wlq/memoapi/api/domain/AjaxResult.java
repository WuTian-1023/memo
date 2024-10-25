package com.wlq.memoapi.api.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 操作消息提醒
 *
 * @author anyisoft
 */
@ApiModel
public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    @ApiModelProperty(value = "状态码。0-成功:301-警告;500-错误")
    public Integer code;

    /**
     * 返回内容
     */
    @ApiModelProperty(value = "消息")
    public String msg;

    /**
     * 数据对象
     */
    @ApiModelProperty(value = "返回数据")
    public T data;


    public Integer getCode() {
        return code;
    }

    public AjaxResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public AjaxResult setData(T data) {
        this.data = data;
        return this;
    }

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(0),
        /**
         * 警告
         */
        WARN(301),

        /**
         * 未登陆或已过期
         */
        UNLOGIN(401),

        /**
         * 未认证
         */
        UNAUTHENTICATION(402),

        /**
         * 未授权
         */
        UNAUTHORIZATION(403),


        /**
         * 错误
         */
        ERROR(500);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象，使其表示一个空消息。
     */
    public AjaxResult() {
    }

    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return this.code == Type.SUCCESS.value();
    }


    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     */
    public AjaxResult(Type type, String msg) {
        this.code = type.value;
        this.msg = msg;
    }

    /**
     * 初始化一个新创建的 AjaxResult 对象
     *
     * @param type 状态类型
     * @param msg  返回内容
     * @param data 数据对象
     */
    public AjaxResult(Type type, String msg, T data) {
        this.code = type.value;
        this.msg = msg;
        this.data = data;
    }


    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    /**
     * 返回成功消息
     *
     * @param msg 返回内容
     * @return 成功消息
     */
    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> AjaxResult<T> success(T data) {
        return AjaxResult.success("操作成功", data);
    }


    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> AjaxResult<T> success(String msg, T data) {
        return new AjaxResult(Type.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult warn(String msg) {
        return AjaxResult.warn(msg, null);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> AjaxResult<T> warn(String msg, T data) {
        return new AjaxResult(Type.WARN, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult error(String msg) {
        return AjaxResult.error(msg, null);
    }


    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> AjaxResult<T> error(String msg, T data) {
        return new AjaxResult(Type.ERROR, msg, data);
    }


    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult unAuthentication() {
        return AjaxResult.unAuthentication("未认证");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult unAuthentication(String msg) {
        return AjaxResult.unAuthentication(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> AjaxResult<T> unAuthentication(String msg, T data) {
        return new AjaxResult(Type.UNAUTHENTICATION, msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult unAuthorization() {
        return AjaxResult.unAuthorization("未授权");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult unAuthorization(String msg) {
        return AjaxResult.unAuthorization(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> AjaxResult<T> unAuthorization(String msg, T data) {
        return new AjaxResult(Type.UNAUTHORIZATION, msg, data);
    }


    /**
     * 返回错误消息
     *
     * @return
     */
    public static AjaxResult unLogin() {
        return AjaxResult.unLogin("未登陆或已过期");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static AjaxResult unLogin(String msg) {
        return AjaxResult.unLogin(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> AjaxResult<T> unLogin(String msg, T data) {
        return new AjaxResult(Type.UNLOGIN, msg, data);
    }

}
