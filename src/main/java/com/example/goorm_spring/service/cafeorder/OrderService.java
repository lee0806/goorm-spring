package com.example.goorm_spring.service.cafeorder;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {
    private final CoffeeService coffeeService;
    private final BreadService breadService;

    public OrderService(CoffeeService coffeeService, BreadService breadService) {
        this.coffeeService = coffeeService;
        this.breadService = breadService;
    }

    public String order(String name) {
        List<String> coffeeList = coffeeService.getCoffeeList();
        List<String> breadList = breadService.getBreadList();

        if (coffeeList.contains(name)) {
            return name + " 커피 주문 완료";
        }
        if (breadList.contains(name)) {
            return name + " 빵 주문 완료";
        }
        return name + " 은 없는 메뉴입니다.";
    }
}
