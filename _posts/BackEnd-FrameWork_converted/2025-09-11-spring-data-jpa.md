---
title: spring-data-jpa
date: 2026-04-27 10:00:00 +0900
categories: [backend, framework]
tags: [spring]
---

# Spring Data JPA
Spring Data JPA는 JPA 기반 저장소를 쉽게 구현할 수 있도록 해주며,
데이터 액세스 기술을 사용하는 스프링 기반 어플리케이션을 쉽게 구축할 수 있다.

## properties 설정하기
```
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=:h2:~/demodb
```

## Yaml 파일 설정
```
spring:
    datasource:
        driver-class-name:
        url:
        username:
        password:
    h2:
        console:
            enabled:
            paht
    jpa:
        hibernate:
            ddl-auto:
            show-sql:
```

## 예시코드
```
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long newsId;

    @Column(name = "news_title", nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String content;
}
```
### 사용되는 어노테이션

**@Getter**
- 데이터를 읽을 때 사용한다
- 데이터 클래스에 적용된다

**@Setter**
- 데이터를 수정할 때 사용한다
- 데이터 클래스에 적용된다

**@AllArgsConstructor**
- 모든 필드값을 배개변수로 하는 생성자를 선언한다
- 데이터 클래스에 적용된다

**@NoArgsconstructor**
- 매개변수가 없는 생성자를 선언한다
- 데이터 클래스에 적용된다

**@Entity**
- 영속성 컨텍스트에 등록하기 위한, 어노테이션이다
- 데이터 클래스에 적용된다

**@Table**
- 엔티티를 특정 테이블에 매핑할 때 사용된다
- 데이터 클래스에 사용된다

**@Id**
- 기본키 설정이다
- 데이터 필드에 사용된다

**@GeneratedValue**
- 기본키 설정 시 타입을 정해준다
- id와 같이 사용되며 데이터 필드에 사용된다

**@Column**
- 다른 키값, 칼럼들을 정해준다
- 데이터 필드에 사용된다

**@Enumerated**
- enum 타입 필드에 사용된다
- 적용시 문자, 정수 등으로 타입을 정할 수 있다
- 데이터 필드에 사용된다

**@Temporal**
- 날짜타입을 매핑시킨다

**@CreationTimestamp**
- 저장된 현재 시간을 저장한다

**@UpdateTimestampe**
- 수정된 시간을 저장한다