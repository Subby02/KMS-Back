package com.moef.kms.document.controller;

import com.moef.kms.document.dto.DocumentCreateDTO;
import com.moef.kms.document.dto.DocumentResponseDTO;
import com.moef.kms.document.entity.DocumentInfo;
import com.moef.kms.document.service.DocumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/document")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    private final DocumentService service;

    /** UT-03: 문서 등록 */
    @PostMapping(
            value    = "/register",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<?> register(
            @Valid @RequestPart("documentInfo") DocumentCreateDTO dto,
            @RequestPart("file") MultipartFile file
    ) {
        if (file.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", "첨부 파일은 필수입니다."));
        }
        service.registerDocument(dto.getDocTitle(), dto.getContent(), file);
        return ResponseEntity.ok(Map.of("message", "문서가 등록되었습니다."));
    }

    /** UT-04: 전체 조회 또는 제목 검색 */
    @GetMapping
    public ResponseEntity<List<DocumentResponseDTO>> list(
            @RequestParam(value = "title", required = false) String title
    ) {
        List<DocumentInfo> docs = (title == null || title.isBlank())
                ? service.listAll()
                : service.searchByTitle(title.trim());

        List<DocumentResponseDTO> results = docs.stream()
                .map(doc -> new DocumentResponseDTO(
                        doc.getDocId(),
                        doc.getDocTitle(),
                        doc.getFileUploadPath(),
                        doc.getCreatedAt()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(results);
    }
}
