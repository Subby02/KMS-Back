package com.moef.kms.stdClub.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class StdClubInfo {

    @Id
    @Column(length = 8)
    private String stdClubId;

    @Column(length = 20, nullable = false)
    private String stdClubName;

    @Column(length = 20)
    private String stdClubInfo;

    @Column(length = 8, nullable = false)
    private String stdClubManagerId;
}
