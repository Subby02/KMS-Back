package com.moef.kms.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
public class ScheduleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleId", nullable = false)
    private Long scheduleId;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "startDateTime", nullable = false)
    private LocalDateTime startDateTime;

    @Column(name = "endDateTime", nullable = false)
    private LocalDateTime endDateTime;

    @Column(name = "priority", nullable = false)
    private int priority = 0;

    @Column(name = "alarmType", length = 20)
    private String alarmType = "None";

    @Column(name = "alarmTime")
    private LocalDateTime alarmTime;

    @Column(name = "alarmTargetId", length = 100)
    private String alarmTargetId;  // 새로 추가된 필드
}
