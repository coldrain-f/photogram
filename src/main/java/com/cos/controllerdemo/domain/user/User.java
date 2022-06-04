package com.cos.controllerdemo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity // 디비에 테이블 생성
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS") // 키워드..?
public class User {

    @Id @GeneratedValue
    private Long id;

    // 길이 제한 20, 유니크 제약조건
    @Column(length = 20, unique = true)
    private String username;
    private String password;

    private String name;
    private String website; // 웹 사이트
    private String bio; // 자기소개
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 프로필 이미지
    private String role; // 권한

    private LocalDateTime createDate;

    @PrePersist // 디비에 INSERT 되기 직전에 실행된다.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}