package com.moef.kms.education.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // getter, setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor
@AllArgsConstructor
public class EducationDTO {

    private String eduType;
    private String eduName;
    private String eduDescription;
    private String eduLocation;
    private int maxApplicant;
    private Date applyStartDate;
    private Date applyEndDate;
    private Date eduStartDate;
    private Date eduEndDate;
    private String educatorName;
    private String educatorContact;
    private String attachmentPath;
    private String referenceType;
    private String videoPath;
    private String videoUrl;
    private String thumbnailPath;

}
