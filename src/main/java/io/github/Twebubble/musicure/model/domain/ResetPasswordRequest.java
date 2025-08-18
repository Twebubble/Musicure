package io.github.Twebubble.musicure.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//生成全参构造器和无参构造器
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResetPasswordRequest {
    private String email;
    private String code;
    private String password;
    private String confirmPassword;
}
