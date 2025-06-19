package com.moef.kms.circle.repository;

import com.moef.kms.circle.dto.CircleResponseDto;
import com.moef.kms.circle.entity.CircleInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CircleRepository extends JpaRepository<CircleInfo, Long> {

    @Query("SELECT new com.moef.kms.circle.dto.CircleResponseDto(c.circleId,c.name,c.description,c.createdBy,COUNT(m),c.createdAt) " +
            "FROM CircleInfo c LEFT JOIN c.members m " +
            "GROUP BY c.circleId ORDER BY COUNT(m) DESC")
    List<CircleResponseDto> findPopularCircles(Pageable pageable);

    @Query("SELECT new com.moef.kms.circle.dto.CircleResponseDto(c.circleId,c.name,c.description,c.createdBy,COUNT(m),c.createdAt) " +
            "FROM CircleInfo c LEFT JOIN c.members m " +
            "GROUP BY c.circleId ORDER BY c.createdAt DESC")
    List<CircleResponseDto> findNewCircles(Pageable pageable);

    @Query("SELECT new com.moef.kms.circle.dto.CircleResponseDto(c.circleId,c.name,c.description,c.createdBy,COUNT(m),c.createdAt) " +
            "FROM CircleInfo c LEFT JOIN c.members m " +
            "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%',:q,'%')) OR LOWER(c.description) LIKE LOWER(CONCAT('%',:q,'%')) " +
            "GROUP BY c.circleId")
    List<CircleResponseDto> searchCircles(@Param("q") String query);
}