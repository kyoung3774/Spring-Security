package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.MemberEntity;

// 해당 인터페이스는 엔티티 클래스를 사용해 만든 객체를 이용하여 DB와 연동한다.
// JpaRepository<>: Spring Data JPA 라이브러리에서 제공하는 인터페이스이며, CRUD, 쿼리메소드,페이징 및 정렬 등의 기능을 제공
// ORM(object Relational Mapping): 객체 지향 프로그래밍 언어의 객체와 관계형 데이터베이스의 테이블 간의 매핑을 처리하는 기법(개념적 기술)
// Hibernate(클래스의 모음집): JPA에서 제공하는 대표적 구현체
public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{

}
