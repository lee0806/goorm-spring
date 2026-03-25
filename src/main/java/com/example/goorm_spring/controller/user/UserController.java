package com.example.goorm_spring.controller.user;

import com.example.goorm_spring.dto.user.UserRequestDto;
import com.example.goorm_spring.dto.user.UserResponseDto;
import com.example.goorm_spring.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // 전체 회원 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    // id로 회원 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    // 이메일로 회원 조회
    @GetMapping("/email")
    public ResponseEntity<UserResponseDto> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    // 회원 등록
    @PostMapping
    public ResponseEntity<UserResponseDto> save(@RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.status(201).body(userService.save(userRequestDto));
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(
            @PathVariable Long id,
            @RequestBody UserRequestDto requestDto
    ) {
        return ResponseEntity.ok(userService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/rollback-test")
    public ResponseEntity<String> rollbackTest() {
        try {
            userService.saveRollback();
            return ResponseEntity.ok("저장이 되었음.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body("예외: " + e.getMessage());
        }
    }
}
