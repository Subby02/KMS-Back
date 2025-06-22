package com.moef.kms.document.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

/**
 * 문서 조회 응답용 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponseDTO {

    private String docId;
    private String docTitle;
    private String fileUploadPath;
    private OffsetDateTime createdAt;
}