package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemberDto;

// 해당 클래스가 비즈니스 로직을 처리하는 클래스라는 명시
// 스프링 컴포넌트 스캔이 동작해 해당 클래스를 스프링 컨테이너에 빈으로 등록
@Service
public class JoinServiceImpl implements JoinService{
	
	@Autowired
	

	@Override
	public void joinUp(MemberDto dto) {
		
	}

}
