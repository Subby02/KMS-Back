package com.moef.kms.schedule.controller;

import com.moef.kms.schedule.dto.ScheduleDto;
import com.moef.kms.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    // 일정 등록
    @PostMapping("/register")
    public ResponseEntity<?> registerSchedule(@RequestBody ScheduleDto dto) {
        if (service.checkEssential(dto)) {
            if (service.checkFormat(dto)) {
                service.registerSchedule(dto);
                return ResponseEntity.ok(Map.of("message", "일정 등록 성공"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "입력 포맷 오류"));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "필수 값 누락"));
        }
    }

    // 일정 상세 조회 (일정 클릭 시)
    @GetMapping("/{id}")
    public ResponseEntity<?> getSchedule(@PathVariable Long id) {
        ScheduleDto dto = service.getScheduleById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(404).body(Map.of("message", "일정이 존재하지 않습니다"));
        }
    }

    // 조건 기반 리스트형 일정 검색
    @PostMapping("/search")
    public ResponseEntity<List<ScheduleDto>> searchSchedules(@RequestBody Map<String, Object> filters) {
        List<ScheduleDto> result = service.searchSchedules(filters);
        return ResponseEntity.ok(result);
    }

    // 월간 일정 조회 (달력 표시용)
    @GetMapping("/monthly")
    public ResponseEntity<List<ScheduleDto>> getMonthlySchedules(
            @RequestParam int year,
            @RequestParam int month
    ) {
        List<ScheduleDto> schedules = service.getSchedulesForMonth(year, month);
        return ResponseEntity.ok(schedules);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        return ResponseEntity.ok(service.getAllSchedules());
    }
}

