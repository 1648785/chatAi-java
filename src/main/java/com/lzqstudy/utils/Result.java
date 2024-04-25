package com.lzqstudy.utils;

/**
 * @author 李正强
 * @version 1.0
 */
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Result() {

    }

    public Result(T data) {
        this.data = data;
    }

    /**
     * 不携带数据的成功返回
     *
     * @return Result
     */
    public static Result success() {
        Result<Object> result = new Result<>();
        result.setCode("200");
        result.setMsg("操作成功");
        return result;
    }

    /**
     * 携带数据的成功返回
     *
     * @param data 返回数据
     * @return Result
     */
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode("200");
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * 不携带数据的失败返回
     *
     * @param code 状态码
     * @param msg  提示信息
     * @return Result
     */
    public static Result error(String code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     * 携带数据的失败返回
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 返回数据
     * @return Result
     */
    public static <T> Result<T> error(String code, String msg, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
