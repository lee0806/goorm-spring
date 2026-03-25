package com.example.goorm_spring.entity.user;

import jakarta.persistence.*;
import lombok.*;


@Entity // DB 테이블과 매핑되는 클래스
@Table(name = "users") // 테이블 이름
@Getter // 모든 필드의 getter를 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@AllArgsConstructor // @Builder가 쓸 전체 생성자를 자동 생성
@Builder
public class User {
    @Id // PK 컬럼
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT: 자동 증가
    private Long id;

    @Column(nullable = false, length = 50) // NOT NULL 조건과 최대 50자
    private String name;

    @Column(nullable = false, unique = true) // NOT NULL 조건과 중복 불가
    private String email;

    public void update(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
