package io.github.Twebubble.musicure.common;

import lombok.Data;

//通用响应封装类，主要用于构建统一格式的 Restful API 响应，提供标准化的 JSON 响应结构

@Data
public class R {

    //HTTP状态码/自定义业务码
    private int code;

    //操作结果消息
    private String message;

    //相应类型
    private String type;

    //成功标志
    private Boolean success;

    //实际返回业务数据
    private Object data;

    //基础成功响应
    public static R success(String message) {
        R r = new R();
        r.setCode(200);
        r.setMessage(message);
        r.setSuccess(true);
        r.setType("success");
        r.setData(null);
        return r;
    }

    //带数据的成功响应
    public static R success(String message, Object data) {
        R r = success(message);
        r.setData(data);
        return r;
    }

    //警告类型响应
    public static R warning(String message) {
        R r = error(message);
        r.setType("warning");
        return r;
    }

    //业务错误响应
    public static R error(String message) {
        R r = success(message);
        r.setSuccess(false);
        r.setType("error");
        return r;
    }

    //系统级错误响应
    public static R fatal(String message) {
        R r = error(message);
        r.setCode(500);
        return r;
    }
}