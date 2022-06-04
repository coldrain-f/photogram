package com.cos.controllerdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 해당 파일을 시큐리티 설정파일로 사용하도록 활성화
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //이미 구현되어 있는 설정을 사용 안 하고 직접 재정의해서 사용하도록 주석 처리
        //super.configure(http);

        http.csrf().disable(); // csrf 비활성화

        http.authorizeRequests()
                .antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**")
                .authenticated() // 위 요청들은 인증(로그인)이 필요함.
                .anyRequest().permitAll() // 나머지 요창들은 인증(로그인)이 필요 없음.
                .and()
                .formLogin()
                .loginPage("/auth/signin") // 인증이 필요한 곳을 요청하면 자동으로 이동시킬 로그인 경로를 지정함.
                .defaultSuccessUrl("/"); // 로그인을 성공하면 이동할 경로 지정
    }
}
