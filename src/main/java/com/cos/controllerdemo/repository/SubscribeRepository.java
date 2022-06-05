package com.cos.controllerdemo.repository;

import com.cos.controllerdemo.domain.subscribe.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer> {

    // 데이터 변경이 필요한 nativeQuery 에는 @Modifying 을 붙여줘야 한다.
    @Modifying
    @Query(
            value = "INSERT INTO SUBSCRIBE(FROM_USER_ID, TO_USER_ID, CREATE_DATE) VALUES(:fromUserId, :toUserId, SYSDATE)",
            nativeQuery = true
    )
    void mSubscribe(int fromUserId, int toUserId); // int -> 변경된 행의 개수만큼 반환, 실패하면 -1

    @Modifying
    @Query(
            value = "DELETE FROM SUBSCRIBE WHERE FROM_USER_ID = :fromUserId AND TO_USER_ID = :toUserId",
            nativeQuery = true
    )
    void mUnSubscribe(int fromUserId, int toUserId);
}
