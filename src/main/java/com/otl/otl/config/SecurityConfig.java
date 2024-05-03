package com.otl.otl.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;

@Configuration
@EnableWebSecurity
@Log4j2
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("Setting up Security Filter Chain...");

        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/","/login","/oauth2/**","error/**","/main", "/index", "/home","/static/**","/img/**","/scss/**","/vendor/**","/아이콘/**", "/resources/**", "/templates/**", "/css/**", "/js/**", "/images/**","/oauth2/authorization/kakao").permitAll()  // 지정된 경로들은 누구나 접근 가능
                        .anyRequest().authenticated()  // 그 외의 모든 요청은 인증을 요구
                )
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/")
                        .defaultSuccessUrl("/main", true)  // 로그인 성공 후 리다이렉션될 기본 URL
                        .failureUrl("/?error=true")  // 로그인 실패 시 index로 리다이렉트하고 쿼리 파라미터 추가
                )
                .logout(logout -> logout

                        .logoutSuccessUrl("/")  // 로그아웃 성공 시 리다이렉션될 URL
                )
                .csrf(csrf -> csrf.disable())  // CSRF 보호 비활성화 (API 서버의 경우 필요)
                .build();

    }
}