package com.cos.controllerdemo.web;

import com.cos.controllerdemo.domain.user.User;
import com.cos.controllerdemo.service.AuthService;
import com.cos.controllerdemo.web.dto.auth.SignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/auth/signin")
    public String signinForm() {
        return "auth/signin";
    }

    @GetMapping("/auth/signup")
    public String signupForm() {
        return "auth/signup";
    }

    // 폼에서 회원가입 버튼을 클릭해도 403 을 응답한다.
    // 이유는? CSRF 토큰이 활성화 되어 있어서.
    // 클라이언트가 GET /auth/signup 을 요청하면 서버는 signup.html 파일을 응답해 준다.
    // 그 때 스프링 시큐리티가 signup.html 을 응답해줄 때 CSRF 토큰을 심어서 응답해준다.
    // CSRF 토큰이 있는 상태로 요청을 해야 스프링 시큐리티는 정상 요청이라는 것을 확인한다.
    // -> SecurityConfig 에서 csrf 를 비활성화 하면 딘다. ( csrf 토큰을 심어주는 로직은 직접 짜야한다. )
    @PostMapping("/auth/signup")
    public String signup(SignupDto signupDto) {
        User user = signupDto.toEntity();
        authService.signup(user);
        return "auth/signin";
    }
}
