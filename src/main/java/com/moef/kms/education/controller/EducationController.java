package com.moef.kms.education.controller;

import com.moef.kms.education.dto.EducationDTO;
import com.moef.kms.education.dto.QueryDTO;
import com.moef.kms.education.entity.EducationInfo;
import com.moef.kms.education.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/education")
public class EducationController {

    private final EducationService service;

    public EducationController(EducationService service) {
        this.service = service;
    }

    @PostMapping("/enrol")
    public ResponseEntity<?> enrolEducation(@RequestBody EducationDTO dto) {
        if (!service.checkEssential(dto)) {
            return ResponseEntity.badRequest().body(Map.of("message", "필수 입력 항목이 누락되었습니다. "));
        }

        if (!service.checkFormat(dto)) {
            return ResponseEntity.badRequest().body(Map.of("message", "입력 형식이 올바르지 않습니다."));
        }

        // 모든 검사를 통과한 경우
        service.enrolEducationInfo(dto);
        return ResponseEntity.ok(Map.of("message", "교육 정보가 성공적으로 등록되었습니다."));
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchEducation(@RequestBody QueryDTO dto) {
        if (!service.checkQuery(dto)) {
            return ResponseEntity.badRequest().body(Map.of("message", "검색 조건이 올바르지 않습니다."));
        }
        List<EducationInfo> results = service.searchEducationInfo(dto);
        return ResponseEntity.ok(results);
    }

}
