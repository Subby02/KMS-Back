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
    private CircleService circleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CircleResponseDto createCircle(@RequestBody CircleDto dto) {
        return circleService.createCircle(dto);
    }

    @GetMapping("/popular")
    public List<CircleResponseDto> getPopularCircles() {
        return circleService.getPopularCircles();
    }

    @GetMapping("/new")
    public List<CircleResponseDto> getNewCircles() {
        return circleService.getNewCircles();
    }

    @GetMapping("/search")
    public List<CircleResponseDto> searchCircles(@RequestParam("query") String query) {
        return circleService.searchCircles(query);
    }
}