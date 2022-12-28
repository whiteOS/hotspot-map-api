package com.entire.hotspotmap.system.main.web;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * 返回结果
 *
 * @author EleAdmin
 * @since 2017-06-10 10:10:50
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {

    private Integer code;

    private String message;

    private T data;

    private String error;

    public ApiResult() {
    }

    public ApiResult(Integer code) {
        this(code, null);
    }

    public ApiResult(Integer code, String message) {
        this(code, message, null);
    }

    public ApiResult(Integer code, String message, T data) {
        this(code, message, data, null);
    }

    public ApiResult(Integer code, String message, T data, String error) {
        setCode(code);
        setMessage(message);
        setData(data);
        setError(error);
    }

    public Integer getCode() {
        return this.code;
    }

    public ApiResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ApiResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public ApiResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getError() {
        return this.error;
    }

    public ApiResult<T> setError(String error) {
        this.error = error;
        return this;
    }

}
