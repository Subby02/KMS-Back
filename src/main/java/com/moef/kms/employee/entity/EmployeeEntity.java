package com.moef.kms.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeEntity {

    @Id
    @Column(name = "emp_id")
    private String empId;

    private String name;

    private String dept;

    private String phone;

    private String email;

    private String status;

    @Column(name = "reg_date")
    private String regDate;
}
