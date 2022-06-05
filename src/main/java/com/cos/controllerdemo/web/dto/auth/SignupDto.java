package com.cos.controllerdemo.web.dto.auth;

import com.cos.controllerdemo.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// DTO: 통신할 때 데이터를 담는 객체를 의마한다.
@Data
public class SignupDto {

    @Size(min = 2, max = 20)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @NotBlank
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
