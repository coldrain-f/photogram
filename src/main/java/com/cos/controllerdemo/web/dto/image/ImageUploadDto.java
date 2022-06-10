package com.cos.controllerdemo.web.dto.image;

import com.cos.controllerdemo.domain.image.Image;
import com.cos.controllerdemo.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class ImageUploadDto {

    // 스프링에서 사용하는 파일 타입...
    // @NotBlank 애노테이션은 MultipartFile 에는 지원되지 않음.
    private MultipartFile file;

    private String caption;

    public Image toEntity(String postImageUrl, User user) {
        return Image.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(user)
                .build();
    }
}
