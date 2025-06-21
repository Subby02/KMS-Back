package com.moef.kms.circle.repository;

import com.moef.kms.circle.entity.CircleMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CircleMemberRepository extends JpaRepository<CircleMember, Long> {
}