package com.example.goorm_spring.service;

import org.springframework.stereotype.Service;

// 유저 등록 서비스
@Service
public class UserService  {
    private final EmailService emailService;

    public UserService(EmailService emailService) {
        this.emailService = emailService;
    }

    // 등록 하는 메서드
    public void register(String email) {
        // 유저를 등록했다는 내용과
        // 이메일을 전송
        System.out.println("유저 등록: " + email);
        emailService.send(email);

    }
}
