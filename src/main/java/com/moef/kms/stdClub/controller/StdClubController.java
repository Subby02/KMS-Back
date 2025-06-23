package com.moef.kms.stdClub.controller;

import com.moef.kms.stdClub.dto.StdClubCreateDto;
import com.moef.kms.stdClub.dto.StdClubResponseDto;
import com.moef.kms.stdClub.service.StdClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;


@RestController
@RequestMapping("/stdClub")
@RequiredArgsConstructor
@Validated
public class StdClubController {

    private final StdClubService stdClubService;

    /**
     * 학습동아리 등록
     */
    @PostMapping("/register")
    public ResponseEntity<Void> registerStdClub(@Valid @RequestBody StdClubCreateDto createDto) {
        try {
            stdClubService.createStdClub(createDto);
            return ResponseEntity.ok().build();  // 응답 본문도 제외
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    /**
     * 모든 학습동아리 조회
     */
    @GetMapping("/search")
    public ResponseEntity<?> searchStdClubs() {
//userId 하드코딩
        String userId = "E001";
        try {
            List<StdClubResponseDto> clubs = stdClubService.getAllStdClubs(userId);
            return ResponseEntity.ok(clubs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("권한이 없습니다.");
        }

    }
}