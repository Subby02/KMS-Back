package com.moef.kms.employee.service;

import com.moef.kms.employee.dto.EmployeeDto;
import com.moef.kms.employee.entity.EmployeeEntity;
import com.moef.kms.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // 직원 등록
    public boolean register(EmployeeDto dto) {
        if (employeeRepository.existsByEmpId(dto.getEmpId())) {
            return false;
        }

        EmployeeEntity entity = new EmployeeEntity();
        entity.setEmpId(dto.getEmpId());
        entity.setName(dto.getName());
        entity.setDept(dto.getDept());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setStatus(dto.getStatus());
        entity.setRegDate(dto.getRegDate());

        employeeRepository.save(entity);
        return true;
    }

    // ID로 직원 조회
    public EmployeeDto findById(String empId) {
        Optional<EmployeeEntity> entityOpt = employeeRepository.findById(empId);
        return entityOpt.map(this::convertToDto).orElse(null);
    }

    // 이름으로 직원 조회
    public List<EmployeeDto> findByName(String name) {
        Optional<EmployeeEntity> entities = employeeRepository.findByName(name);
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Entity → DTO 변환
    private EmployeeDto convertToDto(EmployeeEntity entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmpId(entity.getEmpId());
        dto.setName(entity.getName());
        dto.setDept(entity.getDept());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setStatus(entity.getStatus());
        dto.setRegDate(entity.getRegDate());
        return dto;
    }
}
