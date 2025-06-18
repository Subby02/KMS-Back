package com.moef.kms.schedule.service;

import com.moef.kms.schedule.dto.ScheduleDto;
import com.moef.kms.schedule.entity.ScheduleInfo;
import com.moef.kms.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }

    // 🔹 필수 값 검증
    public boolean checkEssential(ScheduleDto dto) {
        return dto.getTitle() != null && dto.getStartDateTime() != null && dto.getEndDateTime() != null;
    }

    // 🔹 포맷 유효성 검증
    public boolean checkFormat(ScheduleDto dto) {
        return dto.getStartDateTime().isBefore(dto.getEndDateTime());
    }

    // 🔹 일정 등록
    public void registerSchedule(ScheduleDto dto) {
        ScheduleInfo entity = toEntity(dto);
        repository.save(entity);
    }

    // 🔹 단일 조회 (일정 클릭 시)
    public ScheduleDto getScheduleById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    // 🔹 월간 일정 조회 (달력)
    public List<ScheduleDto> getSchedulesForMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDateTime start = ym.atDay(1).atStartOfDay();
        LocalDateTime end = ym.atEndOfMonth().atTime(23, 59, 59);
        List<ScheduleInfo> schedules = repository.findByStartDateTimeBetween(start, end);
        return schedules.stream().map(this::toDto).collect(Collectors.toList());
    }

    // 🔹 조건 기반 일정 검색 (리스트)
    public List<ScheduleDto> searchSchedules(Map<String, Object> filters) {
        String title = (String) filters.getOrDefault("title", "");
        LocalDateTime start = filters.containsKey("startDate") ?
                LocalDate.parse(filters.get("startDate").toString()).atStartOfDay() : null;
        LocalDateTime end = filters.containsKey("endDate") ?
                LocalDate.parse(filters.get("endDate").toString()).atTime(23, 59, 59) : null;

        List<ScheduleInfo> raw;

        if (start != null && end != null) {
            raw = repository.findByTitleContainingAndStartDateTimeBetween(title, start, end);
        } else {
            raw = repository.findByTitleContaining(title);
        }

        return raw.stream().map(this::toDto).collect(Collectors.toList());
    }

    private ScheduleDto toDto(ScheduleInfo entity) {
        return ScheduleDto.builder()
                .scheduleId(entity.getScheduleId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .startDateTime(entity.getStartDateTime())
                .endDateTime(entity.getEndDateTime())
                .priority(entity.getPriority())
                .alarmType(entity.getAlarmType())
                .alarmTime(entity.getAlarmTime())
                .alarmTargetId(entity.getAlarmTargetId())
                .build();
    }

    private ScheduleInfo toEntity(ScheduleDto dto) {
        ScheduleInfo schedule = new ScheduleInfo();
        schedule.setTitle(dto.getTitle());
        schedule.setDescription(dto.getDescription());
        schedule.setStartDateTime(dto.getStartDateTime());
        schedule.setEndDateTime(dto.getEndDateTime());
        schedule.setPriority(dto.getPriority());
        schedule.setAlarmType(dto.getAlarmType());
        schedule.setAlarmTime(dto.getAlarmTime());
        schedule.setAlarmTargetId(dto.getAlarmTargetId());
        return schedule;
    }


}
