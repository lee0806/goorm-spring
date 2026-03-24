package com.example.goorm_spring.repository.cafeorder;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CoffeeRepository {

    // 전체를 반환하는 메서드
    public List<String> findAll() {
        return List.of("아메리카노", "라떼", "돼지바쉐이크"); // DB 연동을 하지 않았기 때문에, 리스트를 반환
    }
}
