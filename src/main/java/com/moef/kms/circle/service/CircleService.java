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
    private CircleRepository repo;

    public CircleResponseDto createCircle(CircleDto dto) {
        CircleInfo c = new CircleInfo();
        c.setName(dto.getName());
        c.setDescription(dto.getDescription());
        c.setCreatedBy(dto.getCreatedBy());
        c.setCreatedAt(LocalDateTime.now());
        repo.save(c);
        return new CircleResponseDto(c.getCircleId(),c.getName(),c.getDescription(),c.getCreatedBy(),c.getMembers().size(),c.getCreatedAt());
    }

    public List<CircleResponseDto> getPopularCircles() {
        return repo.findPopularCircles(PageRequest.of(0,5));
    }

    public List<CircleResponseDto> getNewCircles() {
        return repo.findNewCircles(PageRequest.of(0,5));
    }

    public List<CircleResponseDto> searchCircles(String q) {
        return repo.searchCircles(q);
    }
}