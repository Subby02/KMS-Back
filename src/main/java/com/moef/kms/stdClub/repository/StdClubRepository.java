package com.moef.kms.stdClub.repository;


import com.moef.kms.stdClub.entity.StdClubInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface StdClubRepository extends JpaRepository<StdClubInfo, String>, JpaSpecificationExecutor<StdClubInfo> {


    @Query(value = "SELECT std_club_id FROM std_club_info WHERE std_club_id LIKE 'SC%' ORDER BY CAST(SUBSTRING(std_club_id, 3) AS UNSIGNED) DESC LIMIT 1", nativeQuery = true)
    String findLastStdClubId();

    boolean existsByStdClubName(String stdClubName);

    boolean existsByStdClubManagerId(String stdClubManagerId);
}