package com.example.goorm_spring.dto.user;

import com.example.goorm_spring.entity.user.User;
import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;

    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
