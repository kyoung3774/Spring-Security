package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// Spring에서 기본적으로 주어지는 SecurityConfig가 아닌
// 사용자가 Custom한 SecurityConfig로 적용시킬때 사용하는 Anotation
@Configuration

// 스프링 시큐리티에게 관리 받기위한 Anotation
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean // 스프링 컨테이너에 빈으로 등록
	public BCryptPasswordEncoder bCryptPasswordEncoder () {
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean // 스프링 컨테이너에 빈으로 등록
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			
		http.authorizeHttpRequests((auth) -> auth // authorizeHttpRequests(): 특정 경로에 대한 접근 권한 부여
        		// requestMatchers(): 경로 설정 / 주의사항: 동작순서 위~아래
                .requestMatchers("/", "/login").permitAll() // permitAll(): 접근 권한 부여(모든 유저 가능)
                .requestMatchers("/admin").hasRole("ADMIN") // hasRole(): 접근 권한 부여 단건(인자값 권한만 가능)
                .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER") // hasAnyRole(): 접근 권한 부여 다건(인자값 권한만 가능)
                // anyRequest(): 특정하지 못한 나머지 경로 처리
                .anyRequest().authenticated() // authenticated(): 접근 권한 부여(로그인한 유저만 가능)
                
        );
			
		http.formLogin((auth) -> auth // formLogin(): 권한(로그인)이 필요한 API(페이지)를 요청 했을때 권한이 없을 시 엑세스 거부 페이지가 아닌 로그인 경로 페이지로 유도해주는 메소드
				.loginPage("/login") // loginPage(): 커스텀한 로그인 페이지 유도할 경로를 인수값으로 설정
				.loginProcessingUrl("/loginProc") // loginProcessingUrl(): 커스텀 로그인 <from>에 작성한 데이터를 스프링 시큐리티가 로그인 처리
				.permitAll() // permitAll(): 접근 권한 부여(모든 유저 가능)
		);
		
		// csrf(): 사이트 위조 방지를 위한 함수
		// 로그인 할 때 커스텀 로그인 <from>에 작성한 데이터뿐만이 아닌 csrf토큰이 필요함
		http.csrf((auth) -> auth.disable()); // disable(): csrf토큰의 필요를 없애는 메소드
		
		return http.build();
	}
}
