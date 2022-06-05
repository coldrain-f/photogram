package com.cos.controllerdemo.web;

import com.cos.controllerdemo.config.auth.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class UserController {

    @GetMapping("/user/{id}")
    public String profile(@PathVariable int id) {
        return "user/profile";
    }

    @GetMapping("/user/{id}/update")
    public String update(@PathVariable int id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        // 1. 정석으로 가져오는 방법
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        log.info("직접 찾은 UserDetails = {}", principal.getUser());
        // 2. 애노테이션으로 가져오는 방법 ( 추천 )
        log.info("UserDetails = {}", principalDetails.getUser());

        model.addAttribute("principal", principal.getUser());
        return "user/update";
    }
}
