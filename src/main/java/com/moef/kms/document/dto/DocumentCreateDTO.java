package com.moef.kms.document.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 문서 등록 요청용 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCreateDTO {

    @NotBlank(message = "문서 제목은 필수입니다.")
    @Size(max = 255, message = "문서 제목은 255자 이하여야 합니다.")
    private String docTitle;

    @NotBlank(message = "문서 내용은 필수입니다.")
    private String content;
}
