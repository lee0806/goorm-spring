package com.example.goorm_spring.service.user;

import com.example.goorm_spring.dto.user.UserResponseDto;
import com.example.goorm_spring.dto.user.UserRequestDto;
import com.example.goorm_spring.entity.user.User;
import com.example.goorm_spring.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 전체 회원 조회
    public List<UserResponseDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    // 특정 회원 조회
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다"));
        return UserResponseDto.from(user);
    }

    // 이메일로 회원 조회
    public UserResponseDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        return UserResponseDto.from(user);
    }

    // 회원 저장
    public UserResponseDto save(UserRequestDto requestDto) {
        User user = User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .build();
        return UserResponseDto.from(userRepository.save(user));
    }
}
