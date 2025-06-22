package com.moef.kms.document.service;

import com.moef.kms.document.entity.DocumentInfo;
import com.moef.kms.document.repository.DocumentRepository;
import com.moef.kms.document.upload.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository repository;
    private final FileUpload fileUpload;

    /** 문서 등록 */
    @Transactional
    public void registerDocument(String title, String content, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("첨부파일이 비어 있습니다.");
        }

        String savedFilename;
        try {
            savedFilename = fileUpload.upload(file);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }

        DocumentInfo doc = DocumentInfo.builder()
                .docId(UUID.randomUUID().toString())  // 신규 UUID 생성
                .docTitle(title)
                .content(content)
                .fileUploadPath(savedFilename)
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                // .version(0L)  <-- 제거: JPA가 관리합니다.
                .build();

        repository.save(doc);
    }

    public List<DocumentInfo> listAll() {
        return repository.findAll();
    }

    public List<DocumentInfo> searchByTitle(String title) {
        if (title == null || title.isBlank()) {
            return List.of();
        }
        return repository.findByDocTitleContainingIgnoreCase(title.trim());
    }
}
