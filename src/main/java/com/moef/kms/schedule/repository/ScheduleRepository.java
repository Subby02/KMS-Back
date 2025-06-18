package com.moef.kms.schedule.repository;

import com.moef.kms.schedule.entity.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<ScheduleInfo, Long> {
    List<ScheduleInfo> findByStartDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<ScheduleInfo> findByTitleContaining(String title);
    List<ScheduleInfo> findByTitleContainingAndStartDateTimeBetween(String title, LocalDateTime start, LocalDateTime end);
}

