package com.cos.controllerdemo.web;

import com.cos.controllerdemo.config.auth.PrincipalDetails;
import com.cos.controllerdemo.handler.ex.CustomValidationException;
import com.cos.controllerdemo.service.ImageService;
import com.cos.controllerdemo.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"/", "/image/story"})
    public String story() {
        return "image/story";
    }

    @GetMapping("/image/popular")
    public String popular() {
        return "image/popular";
    }

    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }

    @PostMapping("/image")
    public String imageUpload(ImageUploadDto imageUploadDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }

        // 서비스단에 DTO 를 넘겨도 되나? 아니면.. Image 로 변환해서 넘겨야 하나?
        imageService.imageUpload(imageUploadDto, principalDetails);
        int uploadUserId = principalDetails.getUser().getId();
        return "redirect:/user/" + uploadUserId;
    }
}
