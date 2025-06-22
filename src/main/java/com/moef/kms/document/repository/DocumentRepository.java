package com.moef.kms.document.repository;

import com.moef.kms.document.entity.DocumentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentInfo, String> {
    /** 제목에 키워드가 포함된 문서만 조회 */
    List<DocumentInfo> findByDocTitleContaining(String title);
}