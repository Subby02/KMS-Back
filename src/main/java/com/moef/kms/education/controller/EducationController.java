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
        if (service.checkEssential(dto)) {
            if(service.checkFormat(dto)) {
                service.enrolEducationInfo(dto);

                return ResponseEntity.ok(Map.of("message", "등록 성공"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "포맷 오류"));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "필수 값 오류"));
        }
    }

    @PostMapping("/search")
    public List<EducationInfo> searchEducation(@RequestBody QueryDTO dto) {
        if (service.checkQuery(dto)) {
            return service.searchEducationInfo(dto);
        }
        return List.of();
    }

}
