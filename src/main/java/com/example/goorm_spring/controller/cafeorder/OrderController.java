package com.example.goorm_spring.controller.cafeorder;

import com.example.goorm_spring.service.cafeorder.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String order(@RequestParam String item) {
        return orderService.order(item);
    }

}
