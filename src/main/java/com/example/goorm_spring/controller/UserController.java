package com.example.goorm_spring.controller;


import com.example.goorm_spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerUser(String email) {
        userService.register(email);
        return "등록 완료";
    }
}
