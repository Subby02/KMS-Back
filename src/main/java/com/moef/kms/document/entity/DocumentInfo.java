package com.moef.kms.document.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "document_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentInfo {

    @Id
    @Column(name = "doc_id", length = 36, nullable = false, updatable = false)
    private String docId;

    @Column(name = "doc_title", length = 255, nullable = false)
    private String docTitle;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "file_upload_path", length = 255, nullable = false)
    private String fileUploadPath;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
