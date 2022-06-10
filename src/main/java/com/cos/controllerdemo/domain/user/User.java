package com.cos.controllerdemo.domain.user;

import com.cos.controllerdemo.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity // 디비에 테이블 생성
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS") // 키워드..?
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private int id;

    // 길이 제한 20, 유니크 제약조건
    @Column(length = 20, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private String website; // 웹 사이트
    private String bio; // 자기소개

    @Column(nullable = false)
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 프로필 이미지
    private String role; // 권한

    // 연관관계의 주인이 내가 아니라 user 임을 설정한다.
    // 테이블에 컬럼을 만들지 않는다.
    // User 를 조회하면 User 가 등록한 이미지들을 같이 가지고 온다. ( user_id 로 가지고 옴 )
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Image> images = new ArrayList<>();

    private LocalDateTime createDate;

    @PrePersist // 디비에 INSERT 되기 직전에 실행된다.
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}