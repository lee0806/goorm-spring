package com.example.goorm_spring.service.cafeorder;

import com.example.goorm_spring.repository.cafeorder.BreadRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BreadService {
    private final BreadRepository breadRepository;

    public BreadService(BreadRepository breadRepository) {
        this.breadRepository = breadRepository;
    }

    public List<String> getBreadList() {
        return breadRepository.findAll();
    }
}
