package com.cos.controllerdemo.service;

import com.cos.controllerdemo.domain.user.User;
import com.cos.controllerdemo.handler.ex.CustomException;
import com.cos.controllerdemo.handler.ex.CustomValidationApiException;
import com.cos.controllerdemo.handler.ex.CustomValidationException;
import com.cos.controllerdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User userProfile(int userId) {
        log.info("userId = {}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("해당 프로필 페이지는 없는 페이지입니다."));
    }

    @Transactional
    public User update(int id, User user) {
        // 1. 영속화
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new CustomValidationApiException("찾을 수 없는 ID 입니다."));

        // 2. 영속화된 오프젝트를 수정 - 더티체킹 (업데이트 완료)
        userEntity.setName(user.getName());

        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        userEntity.setPassword(encPassword);
        userEntity.setBio(user.getBio());
        userEntity.setWebsite(user.getWebsite());
        userEntity.setPhone(user.getPhone());
        userEntity.setGender(user.getGender());

        return userEntity;
    } // 더티체킹이 일어나서 업데이트가 완료됨.
}