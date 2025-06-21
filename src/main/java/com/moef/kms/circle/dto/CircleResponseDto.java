package com.moef.kms.circle.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CircleResponseDto {
    private Long circleId;
    private String name;
    private String description;
    private String createdBy;
    private int memberCount;
    private LocalDateTime createdAt;

    public CircleResponseDto(Long circleId, String name, String description,
                             String createdBy, long memberCount, LocalDateTime createdAt) {
        this.circleId = circleId;
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.memberCount = (int) memberCount;
        this.createdAt = createdAt;
    }
}