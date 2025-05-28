package com.moef.kms.education.service;

import com.moef.kms.education.entity.EducationInfo;
import com.moef.kms.education.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository repository;

    public EducationService(EducationRepository repository) {
        this.repository = repository;
    }

    public boolean checkEssential(EducationInfo info) {
        return info.getEduManagerId() != 0
                && info.getEduType() != null && !info.getEduType().isEmpty()
                && info.getEduName() != null && !info.getEduName().isEmpty()
                && info.getEduDescription() != null && !info.getEduDescription().isEmpty()
                && info.getEduLocation() != null && !info.getEduLocation().isEmpty()
                && info.getMaxApplicant() != 0
                && info.getApplyStartDate() != null
                && info.getApplyEndDate() != null
                && info.getEduStartDate() != null
                && info.getEduEndDate() != null
                && info.getEducatorName() != null && !info.getEducatorName().isEmpty()
                && info.getEducatorContact() != null && !info.getEducatorContact().isEmpty()
                && info.getReferenceType() != null && !info.getReferenceType().isEmpty()
                && info.getThumbnailPath() != null && !info.getThumbnailPath().isEmpty();
    }

    public boolean checkFormat(EducationInfo info) {
        return info.getEducatorContact().matches("\\d{3}-\\d{3,4}-\\d{4}")
                && (info.getEduType().equals("오프라인")||info.getEduType().equals("온라인"))
                && (info.getReferenceType().equals("동영상 파일")||info.getReferenceType().equals("URL"));
    }

    public boolean checkQuery() {
        return true;
    }

    public void enrolEducationInfo(EducationInfo info) {
        repository.save(info);
    }

    public List<EducationInfo> searchEducationInfo() {
        return repository.findAll(); // 실제 조건에 따라 수정 가능
    }

}
