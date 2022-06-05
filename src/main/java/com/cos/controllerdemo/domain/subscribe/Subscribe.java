package com.cos.controllerdemo.domain.subscribe;

import com.cos.controllerdemo.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "SUBSCRIBE_UK",
                        columnNames = {"FROM_USER_ID", "TO_USER_ID"})})
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY 안 하면 에러..
    private int id;

    @ManyToOne
    @JoinColumn(name = "FROM_USER_ID")
    private User fromUser; // 구독 하는 사용자

    @ManyToOne
    @JoinColumn(name = "TO_USER_ID")
    private User toUser; // 구독 받는 사용자

    private LocalDateTime createDate;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }
}