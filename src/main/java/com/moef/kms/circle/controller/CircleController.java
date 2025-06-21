package com.moef.kms.circle.controller;

import com.moef.kms.circle.dto.CircleDto;
import com.moef.kms.circle.dto.CircleResponseDto;
import com.moef.kms.circle.service.CircleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/circle")
public class CircleController {
    @Autowired
    private CircleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CircleResponseDto register(@RequestBody CircleDto dto) {
        return service.createCircle(dto);
    }

    @GetMapping("/popular")
    public List<CircleResponseDto> popular() {
        return service.getPopularCircles();
    }

    @GetMapping("/new")
    public List<CircleResponseDto> newest() {
        return service.getNewCircles();
    }

    @GetMapping("/search")
    public List<CircleResponseDto> search(@RequestParam("query") String q) {
        return service.searchCircles(q);
    }
}