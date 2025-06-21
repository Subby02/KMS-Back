package com.moef.kms.taskroom.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class TaskDto {
    private String userId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean alarmSet;
    private boolean visibleSet;
}
