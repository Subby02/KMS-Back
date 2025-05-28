package com.moef.kms.taskroom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class TaskInfo {
    @Id
    private Long taskId;
    private String userId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean alarmSet;
    private boolean visibleSet;
}
