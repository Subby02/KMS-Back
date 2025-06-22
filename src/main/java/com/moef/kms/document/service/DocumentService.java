package com.moef.kms.document.service;

import com.moef.kms.document.entity.DocumentInfo;
import com.moef.kms.document.repository.DocumentRepository;
import com.moef.kms.document.upload.FileUpload;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public void registerDocument(String title, String content, MultipartFile file) {
        try {
            String savedFilename = fileUpload.upload(file);
            DocumentInfo doc = DocumentInfo.builder()
                    .docId(UUID.randomUUID().toString())
                    .docTitle(title)
                    .content(content)
                    .fileUploadPath(savedFilename)
                    .createdAt(OffsetDateTime.now())
                    .updatedAt(OffsetDateTime.now())
                    .version(0L)
                    .build();
            repository.save(doc);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장에 실패했습니다.", e);
        }
    }

    /** 전체 문서 조회 */
    public List<DocumentInfo> listAll() {
        return repository.findAll();
    }

    /** 제목 키워드로 문서 검색 */
    public List<DocumentInfo> searchByTitle(String title) {
        return repository.findByDocTitleContaining(title);
    }
}
