package com.example.goorm_spring.controller.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/hello/params")
    public String helloParams(@RequestParam String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/hello/info")
    public HelloResponse helloInfo() {
        return new HelloResponse("김구름", "Spring Boot 학습 중");
    }

    record HelloResponse(String name, String status) {}

}
