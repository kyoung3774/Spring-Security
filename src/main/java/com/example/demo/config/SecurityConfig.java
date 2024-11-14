package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Spring에서 기본적으로 주어지는 SecurityConfig가 아닌
// 사용자가 Custom한 SecurityConfig로 적용시킬때 사용하는 Anotation
@Configuration

// 스프링 시큐리티에게 관리 받기위한 Anotation
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		// authorizeHttpRequests(): 특정 경로에 대한 접근 권한 부여
        .authorizeHttpRequests((auth) -> auth 
        		// requestMatchers(): 경로 설정 / 주의사항: 동작순서 위~아래
                .requestMatchers("/", "/login").permitAll() // permitAll(): 접근 권한 부여(모든 유저 가능)
                .requestMatchers("/admin").hasRole("ADMIN") // hasRole(): 접근 권한 부여 단건(인자값 권한만 가능)
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // hasAnyRole(): 접근 권한 부여 다건(인자값 권한만 가능)
                // anyRequest(): 특정하지 못한 나머지 경로 처리
                .anyRequest().authenticated() // authenticated(): 접근 권한 부여(로그인한 유저만 가능)
                
        );
			// formLogin(): 권한(로그인)이 필요한 API(페이지)를 요청 했을때 권한이 없을 시 엑세스 거부 페이지가 아닌 로그인 경로 페이지로 유도해주는 메소드
		http.formLogin((auth) -> auth
				.loginPage("/login") // ㅣloginPage(): 커스텀한 로그인 페이지 경로를 인수값으로 설정
				.loginProcessingUrl(null))
		return http.build();
	}
}
