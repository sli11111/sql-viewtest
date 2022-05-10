package com.ysmjjsy.goya.pojo.vo;

import lombok.Data;

/**
 * @author goya
 * @create 2022-03-18 22:21
 */
@Data
public class ResultVO<T> {
    private Integer code;

    private String message;

    private T data;


    @SuppressWarnings("unchecked")
    public static <T> ResultVO<T> succeed(String message, T data) {
        ResultVO result = new ResultVO();
        result.setCode(200);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultVO<T> succeed(Integer code, String message, T data) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultVO<T> fail(String message, T data) {
        ResultVO result = new ResultVO();
        result.setCode(500);
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    @SuppressWarnings("unchecked")
    public static <T> ResultVO<T> fail(Integer code, String message, T data) {
        ResultVO result = new ResultVO();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        return result;
    }
}
