package com.moef.kms.education.service;

import com.moef.kms.education.dto.EducationDTO;
import com.moef.kms.education.dto.QueryDTO;
import com.moef.kms.education.entity.EducationInfo;
import com.moef.kms.education.repository.EducationRepository;
import com.moef.kms.education.specification.QuerySpecification;
import com.moef.kms.employee.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class EducationService {

    private final EducationRepository repository;


    public EducationService(EducationRepository repository) {
        this.repository = repository;
    }

    public boolean checkEssential(EducationDTO dto, MultipartFile videoFile, MultipartFile thumbnailFile) {
        if (videoFile != null && !videoFile.isEmpty()) {
            if (!videoFile.getContentType().startsWith("video/")) {
                return false;
            }
        }
        if (thumbnailFile != null && !thumbnailFile.isEmpty()) {
            if (!thumbnailFile.getContentType().startsWith("image/")) {
                return false;
            }
        }

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
                && dto.getReferenceType() != null && !dto.getReferenceType().isEmpty();
    }

    public boolean checkFormat(EducationDTO dto) {
        return dto.getEducatorContact().matches("\\d{3}-\\d{3,4}-\\d{4}")
                && (dto.getEduType().equals("오프라인")||dto.getEduType().equals("온라인"))
                && (dto.getReferenceType().equals("동영상 파일")||dto.getReferenceType().equals("URL"));
    }

    public boolean checkQuery(QueryDTO dto) {
        if (dto.getApplyStartDate() != null && dto.getApplyEndDate() != null) {
            if (dto.getApplyEndDate().before(dto.getApplyStartDate())) {
                return false;
            }
        }
        if (dto.getEduStartDate() != null && dto.getEduEndDate() != null) {
            if (dto.getEduEndDate().before(dto.getEduStartDate())) {
                return false;
            }
        }
        return true;
    }

    public String saveVideo(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID().toString() + fileExtension;
        String uploadDir = "/path/to/upload/videos/";

        // 디렉토리가 없으면 생성 (필수)
        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(uploadDir, fileName));

        String videoUrl = "http://localhost:8080/videos/" + fileName;
        return videoUrl;
    }

    public String saveThumbnail(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            return null;
        }

        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID().toString() + fileExtension;
        String uploadDir = "/path/to/upload/thumbnails/";

        Files.createDirectories(Paths.get(uploadDir));
        Files.copy(file.getInputStream(), Paths.get(uploadDir, fileName));

        String thumbnailUrl = "http://localhost:8080/thumbnails/" + fileName;
        return thumbnailUrl;
    }

    public void enrolEducationInfo(EducationDTO dto, MultipartFile videoFile, MultipartFile thumbnailFile) throws IOException {
        EducationInfo educationInfo = new EducationInfo();
        educationInfo.setEduManagerId(EmployeeService.getLoginEmployeeId());
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
        String videoPath = saveVideo(videoFile);
        educationInfo.setVideoPath(videoPath);
        educationInfo.setVideoUrl(dto.getVideoUrl());
        String thumbnailPath = saveThumbnail(thumbnailFile);
        System.out.println(thumbnailPath);
        educationInfo.setThumbnailPath(thumbnailPath);
        repository.save(educationInfo);
    }

    public List<EducationInfo> searchEducationInfo(QueryDTO dto) {
        return repository.findAll(QuerySpecification.searchByDto(dto));
    }

}
