package com.cos.controllerdemo.service;

import com.cos.controllerdemo.domain.user.User;
import com.cos.controllerdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// @Service 애노테이션이 붙은 클래스는 트랜잭션 관리를 해준다.
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 함수가 시작하고 종료될 떄 까지 트랜잭션 관리를 해준다.
    @Transactional
    public void signup(User user) {
        // 회원가입 진행
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        user.setRole("ROLE_USER"); // 관리자는 ROLE_ADMIN
        userRepository.save(user);
    }
}
