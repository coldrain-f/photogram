package com.cos.controllerdemo.web.dto.user;

import com.cos.controllerdemo.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateDto {

    @NotBlank
    private String name; // 필수

    @NotBlank
    private String password; // 필수
    private String website;
    private String bio;
    private String phone;
    private String gender;

    // 코드 수정이 필요.
    public User toEntity() {
        return User.builder()
                .name(name) // 이름을 입력하지 않으면 빈 문자열이 들어감. Validation
                .password(password) // 패스워드를 입력하지 않으면 빈 문자열이 들어감. Validation
                .website(website)
                .bio(bio)
                .phone(phone)
                .gender(gender)
                .build();
    }
}
