package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity // 해당 클래스를 Spring Data JPA 라이브러리를 통해 DB에서 테이블화시킴
@Setter
@Getter
public class MemberEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int num; // 회원번호
	
	public String id; // 회원아이디
	
	public String password; // 비밀번호
	
	public String roll; // 회원등급
	
}
