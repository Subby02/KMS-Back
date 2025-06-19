package com.moef.kms.education.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
public class EducationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 5)
    private int eduId;

    @Column(length = 5, nullable = false)
    private int eduManagerId;

    @Column(length = 10, nullable = false)
    private String eduType = "오프라인";

    @Column(length = 20, nullable = false)
    private String eduName;

    @Column(length = 300 , nullable = false)
    private String eduDescription;

    @Column(length = 50, nullable = false)
    private String eduLocation;

    @Column(length = 3, nullable = false)
    private int maxApplicant;

    @Column(length = 3)
    private int currentApplicant = 0;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date applyStartDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date applyEndDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date eduStartDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date eduEndDate;

    @Column(length = 5, nullable = false)
    private String educatorName;

    @Column(length = 13, nullable = false)
    private String educatorContact;

    @Column(length = 50)
    private String attachmentPath;

    @Column(length = 10, nullable = false)
    private String referenceType = "동영상 파일";

    @Column(length = 100)
    private String videoPath;

    @Column(length = 100)
    private String videoUrl;

    @Column(length = 100, nullable = false)
    private String thumbnailPath;

    @ElementCollection
    private List<Integer> applicants;

}
