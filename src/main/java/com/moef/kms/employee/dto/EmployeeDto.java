package com.moef.kms.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDto {
    private String empId;
    private String name;
    private String dept;
    private String phone;
    private String email;
    private String status;
    private String regDate;
}