package com.cos.controllerdemo.config.auth;

import com.cos.controllerdemo.domain.user.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 이곳에 사용자 정보를 담는다.
@Getter
public class PrincipalDetails implements UserDetails {

    //===========================================================================
    // 1. /auth/signup 을 POST 로 요청한다.
    // 2. 스프링 시큐리티가 UserDetailsService 의 loadUserByUsername() 을 호출한다.
    // (이 때, http body 에 username 과 password 를 실어서 요청해야 한다. )
    // 3. loadUserByUsername() 에서 userRepository 로 User 객체를 DB 에서 찾는다.
    // 4. User 가 없으면 쫒아내고 있으면 찾은 User 를 UserDetails 에 담아서 반환한다.
    // 5. 반환된 UserDetails 는 Authentication 에 담긴다.
    // 6. UserDetails 를 담은 Authentication 은 다시 SecurityContextHolder 에 담기고
    // 7. SecurityContextHolder 는 session 영역에 저장된다.
    //===========================================================================

    private User user;

    public PrincipalDetails(User user) {
        this.user = user;
    }

    public void updateUser(User user) {
        this.user = user;
    }

    // 권한이 한 개가 아닐 수 있기 때문에 Collection 을 반환해야 한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> user.getRole());
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override // 계정이 만료되지 않은지를 반환한다.
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정이 잠겨있지 않은지를 반환한다.
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override // 계정의 패스워드가 만료되지 않은지를 반환한다.
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override // 사용가능한 계정인지를 반환한다.
    public boolean isEnabled() {
        return true;
    }
}
