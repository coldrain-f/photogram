package com.cos.controllerdemo.web.api;

import com.cos.controllerdemo.config.auth.PrincipalDetails;
import com.cos.controllerdemo.service.SubscribeService;
import com.cos.controllerdemo.web.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/api/subscribe/{toUserId}")
    public CMRespDto<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId) {
        int fromUserId = principalDetails.getUser().getId();
        subscribeService.subscribe(fromUserId, toUserId);
        return new CMRespDto<>(1, "구독하기 성공", null);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/api/subscribe/{toUserId}")
    public CMRespDto<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable int toUserId) {
        int fromUserId = principalDetails.getUser().getId();
        subscribeService.unSubscribe(fromUserId, toUserId);
        return new CMRespDto<>(1, "구독취소하기 성공", null);
    }
}
