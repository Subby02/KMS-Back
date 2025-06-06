package com.moef.kms.education.specification;

import com.moef.kms.education.dto.QueryDTO;
import com.moef.kms.education.entity.EducationInfo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class QuerySpecification {

    public static Specification<EducationInfo> searchByDto(QueryDTO dto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (dto.getEduType() != null && !dto.getEduType().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("eduType"), dto.getEduType()));
            }
            if (dto.getEduName() != null && !dto.getEduName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("eduName"), "%" + dto.getEduName() + "%"));
            }
            if (dto.getEduLocation() != null && !dto.getEduLocation().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("eduLocation"), "%" + dto.getEduLocation() + "%"));
            }
            if (dto.getApplyStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("applyStartDate"), dto.getApplyStartDate()));
            }
            if (dto.getApplyEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("applyEndDate"), dto.getApplyEndDate()));
            }
            if (dto.getEduStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("eduStartDate"), dto.getEduStartDate()));
            }
            if (dto.getEduEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("eduEndDate"), dto.getEduEndDate()));
            }
            if (dto.getEducatorName() != null && !dto.getEducatorName().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("educatorName"), "%" + dto.getEducatorName() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
