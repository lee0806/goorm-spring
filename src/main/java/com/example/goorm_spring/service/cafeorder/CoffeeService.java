package com.example.goorm_spring.service.cafeorder;

import com.example.goorm_spring.repository.cafeorder.CoffeeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoffeeService {
    // 사용할 필드 선언
    private final CoffeeRepository coffeeRepository;

    // 외부에서 CoffeeRepository를 받아서 선언한 필드에 저장
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<String> getCoffeeList() {
        return coffeeRepository.findAll();
    }

}
