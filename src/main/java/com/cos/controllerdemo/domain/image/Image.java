package com.cos.controllerdemo.domain.image;

import com.cos.controllerdemo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String caption; // 설명

    // 사진을 전송받아서 그 사진을 서버의 특정 폴더에 저장
    // (DB 에는 경로를 저장)
    private String postImageUrl;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    private LocalDateTime createDate;

    // ======= 추가 예정 =======
    // 이미지 좋아요
    // 댓글

    @PrePersist
    public void createDate() { // 항상 DB 에는 등록일이 들어가야 함.
        this.createDate = LocalDateTime.now();
    }
}
