package com.moef.kms.employee.controller;

import com.moef.kms.employee.dto.EmployeeDto;
import com.moef.kms.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // 직원 가입
    @PostMapping
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDto employeeDto) {
        boolean success = employeeService.register(employeeDto);
        if (success) {
            return ResponseEntity.ok("가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디 중복");
        }
    }

    // 직원 ID로 조회
    @GetMapping("/{empId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable String empId) {
        EmployeeDto dto = employeeService.findById(empId);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 직원 이름으로 조회
    @GetMapping("/search")
    public ResponseEntity<List<EmployeeDto>> getEmployeesByName(@RequestParam String name) {
        List<EmployeeDto> result = employeeService.findByName(name);
        return ResponseEntity.ok(result);
    }
}

