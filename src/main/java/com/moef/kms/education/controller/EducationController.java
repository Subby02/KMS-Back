package com.moef.kms.education.controller;

import com.moef.kms.education.entity.EducationInfo;
import com.moef.kms.education.service.EducationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/education")
public class EducationController {

    private final EducationService service;

    public EducationController(EducationService service) {
        this.service = service;
    }

    @PostMapping("/enrol")
    public ResponseEntity<?> enrolEducation(@RequestBody EducationInfo info) {
        if (service.checkEssential(info)) {
            if(service.checkFormat(info)) {
                service.enrolEducationInfo(info);

                return ResponseEntity.ok(Map.of("message", "등록 성공"));
            } else {
                return ResponseEntity.badRequest().body(Map.of("message", "포맷 오류"));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "필수 값 오류"));
        }
    }

//    @PostMapping("/search")
//    public List<EducationInfo> searchEducation(@RequestBody Query query) {
//        if (service.checkQuery(query)) {
//            return service.searchEducationInfo(query);
//        }
//        return List.of();
//    }

}
