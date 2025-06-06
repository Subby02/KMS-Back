package com.moef.kms.education.repository;

import com.moef.kms.education.entity.EducationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EducationRepository extends JpaRepository<EducationInfo, Integer> , JpaSpecificationExecutor<EducationInfo> {
}
