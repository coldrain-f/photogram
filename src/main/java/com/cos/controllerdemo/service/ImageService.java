package com.cos.controllerdemo.service;

import com.cos.controllerdemo.config.auth.PrincipalDetails;
import com.cos.controllerdemo.domain.image.Image;
import com.cos.controllerdemo.domain.user.User;
import com.cos.controllerdemo.handler.ex.CustomException;
import com.cos.controllerdemo.repository.ImageRepository;
import com.cos.controllerdemo.repository.UserRepository;
import com.cos.controllerdemo.web.dto.image.ImageUploadDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    // 이미지 파일 저장 경로는 외부로 분리해야 한다.
    // 이유 1. .java 파일은 .class 로 빌드 되는데 빠르게 끝나지만
    // 이미지 파일은 용량이 상대적으로 크기 때문에 target 폴더로 빌드되는데 오래걸리기 때문. (용량 문제)
    // 이유 2. 이미지 파일을 업로드하고 빌드되는 시간이 오래걸리면
    // 사진을 화면에서 렌더링 했을 때 렌더링 되는 시간보다 빌드되는 시간이 오래 걸려서 엑박이 표시될 수 있기 때문. (엑박 문제)

    @Value("${file.path}") // 스프링꺼 ( final 붙이면 안 됨 )
    private String uploadFolder;

    @Transactional
    public void imageUpload(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
        UUID uuid = UUID.randomUUID();
        // 파일 이름이 1.jpg 이라면 originalFilename 은 1.jpg 이다.
        // 동일한 파일명이 들어오면 덮어씌워지기 때문에 UUID_파일명 으로 만들어준다.
        String imageFileName = uuid + "_" + imageUploadDto.getFile().getOriginalFilename();
        log.info("imageFileName = {}", imageFileName);

        // 실제 어디에 저장이 될건지 경로를 지정해야 한다.
        // 경로는 application.yml 에 지정되어 있다.
        // TODO: 2022-06-06  Path 와 Paths 가 뭔지 알아보기
        Path imageFilePath = Paths.get(uploadFolder + imageFileName);

        // I/O 사용시에는 예외가 발생할 수 있으므로 try catch 로 감싸줘야 한다.
        try {
            // 1. 저장될 파일 경로 2. 저장될 파일
            Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
        } catch(IOException e) {
            e.printStackTrace();
        }

        // 이미지 테이블에 저장
        Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser());
        imageRepository.save(image);
    }
}