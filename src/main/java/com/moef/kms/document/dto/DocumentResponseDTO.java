package com.moef.kms.document.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class DocumentResponseDTO {
    private String docId;
    private String docTitle;
    private String fileUploadPath;
    private OffsetDateTime createdAt;
}
