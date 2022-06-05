package com.cos.controllerdemo.web.api;

import com.cos.controllerdemo.config.auth.PrincipalDetails;
import com.cos.controllerdemo.domain.user.User;
import com.cos.controllerdemo.handler.ex.CustomValidationApiException;
import com.cos.controllerdemo.handler.ex.CustomValidationException;
import com.cos.controllerdemo.service.UserService;
import com.cos.controllerdemo.web.dto.CMRespDto;
import com.cos.controllerdemo.web.dto.user.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("/api/user/{id}")
    public CMRespDto<User> update(
            @PathVariable int id,
            @Valid UserUpdateDto userUpdateDto,
            BindingResult bindingResult,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            throw new CustomValidationApiException("유효성 검사 실패함", errorMap);
        }

        User userEntity = userService.update(id, userUpdateDto.toEntity());

        // 세션 정보도 변경해 줘야 view 에서도 변경된다.
        principalDetails.updateUser(userEntity);
        return new CMRespDto<>(1, "회원수정완료", userEntity);
    }
}
