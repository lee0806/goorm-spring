package com.example.goorm_spring.service.user;

import com.example.goorm_spring.dto.user.UserResponseDto;
import com.example.goorm_spring.dto.user.UserRequestDto;
import com.example.goorm_spring.entity.user.User;
import com.example.goorm_spring.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    // 회원정보 업데이트
    @Transactional
    public UserResponseDto update(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));
        user.update(requestDto.getName(), requestDto.getEmail());
        return UserResponseDto.from(user);
    }

    // 회원 삭제
    @Transactional
    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        userRepository.delete(user);
    }

    // 트랜잭션 테스트
    @Transactional
    public void saveRollback() {
        User user1 = User.builder()
                .name("테스트 유저 1")
                .email("test1@test.com")
                .build();
        userRepository.save(user1);

        System.out.println("첫 번째 유저를 저장 했습니다." + user1.getName());

        if (true) {
            throw new RuntimeException("롤백 테스트입니다.");
        }

        User user2 = User.builder()
                .name("테스트 유저 2")
                .email("test2@test.com")
                .build();
        userRepository.save(user2);

    }
}
