package com.cos.controllerdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String uploadFolder;

    // TODO: 2022-06-06 따로 정리하기
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // C:/workspace/springbootwork/upload/
        // file:///C:/workspace/springbootwork/upload/
        log.info("uploadFolder = {}", uploadFolder);
        //WebMvcConfigurer.super.addResourceHandlers(registry);

        // HTML 에서 /upload/파일.jpg 를 사용하면
        // file:///C:/workspace/springbootwork/upload/파일.jpg 로 변경해 준다.
        // TODO: 2022-06-06 동작 안 함..
        registry.addResourceHandler("/upload/**") // /upload/** 이런 요청이 들어오면
                .addResourceLocations("file:///" + uploadFolder)
                .setCachePeriod(60 * 10 * 6) // 1시간동안 이미지 캐싱
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
