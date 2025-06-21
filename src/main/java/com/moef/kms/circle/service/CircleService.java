package com.moef.kms.circle.service;

import com.moef.kms.circle.dto.CircleDto;
import com.moef.kms.circle.dto.CircleResponseDto;
import com.moef.kms.circle.entity.CircleInfo;
import com.moef.kms.circle.entity.CircleMember;
import com.moef.kms.circle.repository.CircleRepository;
import com.moef.kms.circle.repository.CircleMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CircleService {
    @Autowired
    private CircleRepository repo;

    @Autowired
    private CircleMemberRepository memberRepo;

    public CircleResponseDto createCircle(CircleDto dto) {
        CircleInfo circle = new CircleInfo();
        circle.setName(dto.getName());
        circle.setDescription(dto.getDescription());
        circle.setCreatedBy(dto.getCreatedBy());
        circle.setCreatedAt(LocalDateTime.now());
        CircleInfo saved = repo.save(circle);

        CircleMember member = new CircleMember();
        member.setCircle(saved);
        member.setUserId(dto.getCreatedBy());
        member.setJoinedAt(LocalDateTime.now());
        memberRepo.save(member);

        return new CircleResponseDto(
                saved.getCircleId(),
                saved.getName(),
                saved.getDescription(),
                saved.getCreatedBy(),
                1,
                saved.getCreatedAt()
        );
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