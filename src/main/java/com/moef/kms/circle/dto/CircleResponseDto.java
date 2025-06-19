package com.moef.kms.circle.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CircleResponseDto {
    private Long circleId;
    private String name;
    private String description;
    private int memberCount;
    private LocalDateTime createdAt;

    public CircleResponseDto(Long circleId,
                             String name,
                             String description,
                             long memberCount,
                             LocalDateTime createdAt) {
        this.circleId = circleId;
        this.name = name;
        this.description = description;
        this.memberCount = (int) memberCount;
        this.createdAt = createdAt;
    }
}