package com.moef.kms.document.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DocumentCreateDTO {

    @NotBlank(message = "문서 제목은 필수입니다.")
    private String docTitle;

    private String content;
}
