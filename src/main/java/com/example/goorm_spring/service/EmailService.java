package com.example.goorm_spring.service;


import org.springframework.stereotype.Service;

// 이메일을 보내는 서비스
@Service
public class EmailService {
    public void send(String to) {
        // 누구에게 이메일을 보내는지 알 수 있는 함수
        System.out.println("이메일 발송: " + to);
    }
}
