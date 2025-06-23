package com.moef.kms.stdClub.service;

import com.moef.kms.education.repository.EducationRepository;
import com.moef.kms.employee.entity.EmployeeEntity;
import com.moef.kms.stdClub.dto.StdClubCreateDto;
import com.moef.kms.stdClub.dto.StdClubResponseDto;
import com.moef.kms.stdClub.entity.StdClubInfo;
import com.moef.kms.stdClub.repository.StdClubRepository;
import com.moef.kms.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StdClubService {

    private final StdClubRepository stdClubRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public void createStdClub(StdClubCreateDto createDto) {
        // 동아리 이름 중복 체크
        if (stdClubRepository.existsByStdClubName(createDto.getStdClubName())) {
            throw new IllegalArgumentException("이미 존재하는 동아리 이름입니다: " + createDto.getStdClubName());
        }
        // 직원 이름으로 empId 조회
        EmployeeEntity manager = employeeRepository.findByName(createDto.getStdClubManagerName())
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 직원이 존재하지 않습니다: " + createDto.getStdClubManagerName()));


        // DTO -> Entity 변환
        StdClubInfo stdClubInfo = new StdClubInfo();
        stdClubInfo.setStdClubName(createDto.getStdClubName());
        stdClubInfo.setStdClubInfo(createDto.getStdClubInfo());
        stdClubInfo.setStdClubManagerId(manager.getEmpId());
        // 저장
        save(stdClubInfo);
        ResponseEntity.status(HttpStatus.CREATED);
    }

    public void save(StdClubInfo stdClubInfo) {
        String lastId = stdClubRepository.findLastStdClubId(); // "SC0009" 이런 식으로 가장 큰 아이디 조회
        int nextNum = 1;
        if (lastId != null) {
            nextNum = Integer.parseInt(lastId.substring(2)) + 1; // "SC" 뗀 숫자 부분만 증가
        }
        String newId = String.format("SC%04d", nextNum);  // SC0001, SC0002 ...
        stdClubInfo.setStdClubId(newId);

        stdClubRepository.save(stdClubInfo);
    }

    public List<StdClubResponseDto> getAllStdClubs(String userId) throws AccessDeniedException {
        validateUserAuthority(userId);

        //  권한이 확인된 경우 전체 리스트 조회 및 DTO 변환
        List<StdClubInfo> clubs = stdClubRepository.findAll();

        return clubs.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }


    private StdClubResponseDto convertToResponseDto(StdClubInfo entity) {
        Optional<EmployeeEntity> employeeOpt = employeeRepository.findById(entity.getStdClubManagerId());
        String name = employeeOpt.map(EmployeeEntity::getName).orElse("이름 없음");

        return new StdClubResponseDto(
                entity.getStdClubId(),
                entity.getStdClubName(),
                entity.getStdClubInfo(),
                name
        );
    }
    private void validateUserAuthority(String userId) throws AccessDeniedException {

        if (userId == null ) {
            throw new AccessDeniedException("로그인이 필요합니다.");
        }
        boolean EduManager = employeeRepository.existsByEmpId(userId);

        boolean isStdClubManager = stdClubRepository.existsByStdClubManagerId(userId);
        if (!(isStdClubManager||EduManager)) {
            throw new AccessDeniedException("권한이 없습니다: 관리자가 아닙니다.");
        }

    }

}