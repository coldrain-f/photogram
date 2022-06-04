package com.cos.controllerdemo.repository;

import com.cos.controllerdemo.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 가 없어도 JpaRepository 를 상속했다면 IoC 에 등록된다.
public interface UserRepository extends JpaRepository<User, Long> {

}
