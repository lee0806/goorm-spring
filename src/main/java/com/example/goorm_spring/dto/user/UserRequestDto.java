package com.example.goorm_spring.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자
// 클라이언트가 서버에게 받을 때, 즉 요청할 때의 DTO
public class UserRequestDto {
    private String name;
    private String email;
}
