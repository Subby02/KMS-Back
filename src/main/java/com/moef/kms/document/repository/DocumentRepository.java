package com.moef.kms.document.repository;

import com.moef.kms.document.entity.DocumentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<DocumentInfo, String> {
    List<DocumentInfo> findByDocTitleContainingIgnoreCase(String title);
}
