package com.moef.kms.stdClub.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StdClubResponseDto {
    private String stdClubId;
    private String stdClubName;
    private String stdClubInfo;
    private String stdClubManagerName;
}
