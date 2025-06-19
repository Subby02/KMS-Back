package com.moef.kms.education.controller;

import com.moef.kms.education.dto.EducationDTO;
import com.moef.kms.education.dto.QueryDTO;
import com.moef.kms.education.entity.EducationInfo;
import com.moef.kms.education.service.EducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping(value = "/enrol", consumes = "multipart/form-data") // multipart/form-data를 소비한다고 명시
    public ResponseEntity<?> enrolEducationWithVideo(
            @RequestPart("educationInfo") EducationDTO dto,
            @RequestPart(value = "videoFile", required = false) MultipartFile videoFile, // 영상 파일
            @RequestPart(value = "thumbnailFile", required = false) MultipartFile thumbnailFile) { // 썸네일 파일 (선택 사항)

        // 1. 필수 입력 항목 및 형식 검사
        if (!service.checkEssential(dto, videoFile, thumbnailFile)) {
            return ResponseEntity.badRequest().body(Map.of("message", "필수 입력 항목이 누락되었습니다."));
        }

        if (!service.checkFormat(dto)) {
            return ResponseEntity.badRequest().body(Map.of("message", "입력 형식이 올바르지 않습니다."));
        }

        try {
            service.enrolEducationInfo(dto, videoFile, thumbnailFile);
            return ResponseEntity.ok(Map.of("message", "교육 정보와 영상 파일이 성공적으로 등록되었습니다."));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "교육 정보 등록 중 오류가 발생했습니다: " + e.getMessage()));
        }
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
