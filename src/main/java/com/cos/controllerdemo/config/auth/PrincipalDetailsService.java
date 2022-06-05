package com.cos.controllerdemo.config.auth;

import com.cos.controllerdemo.domain.user.User;
import com.cos.controllerdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

// /auth/signin 을 POST 방식으로 요청하면 이곳에서 낚아챈다.
// http body 에 username 과 password 를 실어서 요청해야 한다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 1. password 는 시큐리티가 알아서 확인한다.
    // 2. 정상적으로 UserDetails 를 반환하면 시큐리티는 UserDetails 을 세션에 저장한다.
    // 정확히는 UserDetails 의 구현체인 PrincipalDetails 를 세션에 저장해 놓음.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // DB 에서 username 으로 사용자가 있는지 확인한다.
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            return null;
        }
        return new PrincipalDetails(user.get());
    }
}
