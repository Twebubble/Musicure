package io.github.Twebubble.musicure.model.request;

import lombok.Data;

import java.util.Date;

//所有有关用户的属性
@Data
public class ConsumerRequest {
    private Integer id;

    private String username;

    private String oldPassword; //用到用户旧密码

    private String password;

    private Byte sex;

    private String phoneNum;

    private String email;

    private Date birth;

    private String introduction;

    private String location;

    private String avator;

    private Date createTime;
}
