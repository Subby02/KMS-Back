package com.moef.kms.education.service;

import com.moef.kms.education.dto.EducationDTO;
import com.moef.kms.education.dto.QueryDTO;
import com.moef.kms.education.entity.EducationInfo;
import com.moef.kms.education.repository.EducationRepository;
import com.moef.kms.education.specification.QuerySpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository repository;
//    private final QEducationInfo educationInfo = QEducationInfo.educationInfo;


    public EducationService(EducationRepository repository) {
        this.repository = repository;
    }

    public boolean checkEssential(EducationDTO dto) {
        return dto.getEduType() != null && !dto.getEduType().isEmpty()
                && dto.getEduName() != null && !dto.getEduName().isEmpty()
                && dto.getEduDescription() != null && !dto.getEduDescription().isEmpty()
                && dto.getEduLocation() != null && !dto.getEduLocation().isEmpty()
                && dto.getMaxApplicant() != 0
                && dto.getApplyStartDate() != null
                && dto.getApplyEndDate() != null
                && dto.getEduStartDate() != null
                && dto.getEduEndDate() != null
                && dto.getEducatorName() != null && !dto.getEducatorName().isEmpty()
                && dto.getEducatorContact() != null && !dto.getEducatorContact().isEmpty()
                && dto.getReferenceType() != null && !dto.getReferenceType().isEmpty()
                && dto.getThumbnailPath() != null && !dto.getThumbnailPath().isEmpty();
    }

    public boolean checkFormat(EducationDTO dto) {
        return dto.getEducatorContact().matches("\\d{3}-\\d{3,4}-\\d{4}")
                && (dto.getEduType().equals("오프라인")||dto.getEduType().equals("온라인"))
                && (dto.getReferenceType().equals("동영상 파일")||dto.getReferenceType().equals("URL"));
    }

    public boolean checkQuery(QueryDTO dto) {
        return true;
    }

    public void enrolEducationInfo(EducationDTO dto) {
        EducationInfo educationInfo = new EducationInfo();
        educationInfo.setEduManagerId(10001);
        educationInfo.setEduName(dto.getEduName());
        educationInfo.setEduDescription(dto.getEduDescription());
        educationInfo.setEduLocation(dto.getEduLocation());
        educationInfo.setMaxApplicant(dto.getMaxApplicant());
        educationInfo.setApplyStartDate(dto.getApplyStartDate());
        educationInfo.setApplyEndDate(dto.getApplyEndDate());
        educationInfo.setEduStartDate(dto.getEduStartDate());
        educationInfo.setEduEndDate(dto.getEduEndDate());
        educationInfo.setEducatorName(dto.getEducatorName());
        educationInfo.setEducatorContact(dto.getEducatorContact());
        educationInfo.setAttachmentPath(dto.getAttachmentPath());
        educationInfo.setReferenceType(dto.getReferenceType());
        educationInfo.setVideoPath(dto.getVideoPath());
        educationInfo.setVideoUrl(dto.getVideoUrl());
        educationInfo.setThumbnailPath(dto.getThumbnailPath());
        repository.save(educationInfo);
    }

    public List<EducationInfo> searchEducationInfo(QueryDTO dto) {
        return repository.findAll(QuerySpecification.searchByDto(dto));
    }

}
