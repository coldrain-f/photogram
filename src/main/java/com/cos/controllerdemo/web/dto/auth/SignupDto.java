package com.cos.controllerdemo.web.dto.auth;

import com.cos.controllerdemo.domain.user.User;
import lombok.Data;

// DTO: 통신할 때 데이터를 담는 객체를 의마한다.
@Data
public class SignupDto {

    private String username;
    private String password;
    private String email;
    private String name;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .email((this.email))
                .name(this.name)
                .build();
    }

}
