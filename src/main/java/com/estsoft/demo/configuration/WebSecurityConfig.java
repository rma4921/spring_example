package com.estsoft.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer configure() {      // 1. 스프링 시큐리티 기능 비활성화
        return web -> web.ignoring()
                .requestMatchers("/static/**");
    }

    // 2. 특정 HTTP 요청에 대한 웹 기반 보안 구성
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> // 인증, 인가 설정
                        auth.requestMatchers("/login", "/signup", "/user").permitAll()
                                .requestMatchers("/books", "/books/**").permitAll() // 테스트용 나중에 삭제하기!!
                                .requestMatchers("/api/external", "/api/external/test").permitAll() // 테스트용
                                .requestMatchers("/new-article").hasRole("ADMIN")
                                .anyRequest().authenticated())
                .formLogin(auth -> auth.loginPage("/login") // 폼 기반 로그인 설정
                        .defaultSuccessUrl("/articles"))
                .logout(auth -> auth.logoutSuccessUrl("/login") // 로그아웃 설정
                        .invalidateHttpSession(true)
                        .clearAuthentication(true))
                .csrf(auth -> auth.disable()); // csrf 비활성화
        return httpSecurity.build();
    }

    // 3. 패스워드 인코더로 사용할 빈 등록 (암호화)
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}