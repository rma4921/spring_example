package com.estsoft.demo.interceptor;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 인터셉터 등록
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new FirstInterceptor())
                .addPathPatterns("/test")
                .order(1);
        registry.addInterceptor(new SecondInterceptor())
                .addPathPatterns("/test")
                .order(2);
    }
}
