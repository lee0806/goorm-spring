package com.example.goorm_spring.repository.cafeorder;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BreadRepository {

    public List<String> findAll() {
        return List.of("크로아상", "바게트", "식빵");
    }

}
