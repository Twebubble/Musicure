package io.github.Twebubble.musicure.model.request;

import lombok.Data;

//数据传输对象（DTO），用于接收前端登录请求的数据。
@Data
public class AdminRequest {
    private Integer id;

    private  String username;

    private String password;
}
