package com.moef.kms.employee.repository;

import com.moef.kms.employee.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    boolean existsByEmpId(String empId);
    Optional<EmployeeEntity> findByName(String name);
}
