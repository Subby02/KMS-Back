package com.moef.kms.circle.service;

import com.moef.kms.circle.dto.CircleDto;
import com.moef.kms.circle.dto.CircleResponseDto;
import com.moef.kms.circle.entity.CircleInfo;
import com.moef.kms.circle.repository.CircleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CircleService {

    @Autowired
    private CircleRepository circleRepository;

    public CircleResponseDto createCircle(CircleDto dto) {
        CircleInfo circle = new CircleInfo();
        circle.setName(dto.getName());
        circle.setDescription(dto.getDescription());
        circle.setCreatedAt(LocalDateTime.now());
        circleRepository.save(circle);
        return new CircleResponseDto(
                circle.getCircleId(),
                circle.getName(),
                circle.getDescription(),
                circle.getMembers().size(),
                circle.getCreatedAt()
        );
    }

    public List<CircleResponseDto> getPopularCircles() {
        return circleRepository.findPopularCircles(PageRequest.of(0, 5));
    }

    public List<CircleResponseDto> getNewCircles() {
        return circleRepository.findNewCircles(PageRequest.of(0, 5));
    }

    public List<CircleResponseDto> searchCircles(String query) {
        return circleRepository.searchCircles(query);
    }
}