package com.moef.kms.stdClub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StdClubCreateDto {

    // stdClubId는 자동증가이므로 DTO에서 제외

    @NotBlank(message = "동아리명은 필수입니다.")
    @Size(max = 20, message = "동아리명은 20자 이하여야 합니다.")
    private String stdClubName;

    @Size(max = 20, message = "동아리 정보는 20자 이하여야 합니다.")
    private String stdClubInfo;

    @NotBlank(message = "관리자 이름은 필수입니다.")
    @Size(max = 8, message = "관리자 이름은 8자 이하여야 합니다.")
    private String stdClubManagerName;
}
