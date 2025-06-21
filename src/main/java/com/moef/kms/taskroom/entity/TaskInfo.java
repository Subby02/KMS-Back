package com.moef.kms.taskroom.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "task_info")
public class TaskInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long taskId;

    @Column(length = 20, nullable = false)
    private String userId;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private boolean alarmSet;

    @Column
    private boolean visibleSet;
}
