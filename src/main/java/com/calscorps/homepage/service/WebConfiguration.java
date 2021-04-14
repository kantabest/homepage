package com.calscorps.homepage.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/views/index/KR");
        registry.addRedirectViewController("/views", "/views/index/KR");
        registry.addRedirectViewController("/views", "/views/index/KR");
    }

    @Override
    // 연결된 다운로드 파일은 네트워크 드라이브로 연결되어 있어 해당 폴더를 리소스 핸들러에 추가한다.
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/link/sdc112/**")                
                .addResourceLocations("file:///x:/"); 


    }

    
}
