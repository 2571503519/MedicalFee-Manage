package com.jackaroo.spring_boot_demo.util;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * @author JackarooZhang
 * @date 2018/6/9 10:54
 */
public class AjaxResponse<T> implements Serializable {

    private Integer status;

    private String msg;

    private T data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private AjaxResponse(int status) {
        this.status = status;
    }

    private AjaxResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private AjaxResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> AjaxResponse<T> createBySuccess() {
        return new AjaxResponse<T>(Constant.ResponseStatus.SUCCESS.getCode());
    }

    public static <T> AjaxResponse<T> createBySuccess(String msg) {
        return new AjaxResponse<T>(Constant.ResponseStatus.SUCCESS.getCode(), msg);
    }

    public static <T> AjaxResponse<T> createBySuccess(String msg, T data) {
        return new AjaxResponse<T>(Constant.ResponseStatus.SUCCESS.getCode(), msg, data);
    }

    public static <T> AjaxResponse<T> createByFail() {
        return new AjaxResponse<T>(Constant.ResponseStatus.FAIL.getCode());
    }

    public static <T> AjaxResponse<T> createByFail(String msg) {
        return new AjaxResponse<T>(Constant.ResponseStatus.FAIL.getCode(), msg);
    }

    public static <T> AjaxResponse<T> createByFail(String msg, T data) {
        return new AjaxResponse<T>(Constant.ResponseStatus.FAIL.getCode(), msg, data);
    }


}
