package com.moef.kms.document.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUpload {

    private final String uploadDir = "uploads"; // 실제 파일 저장 경로

    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일입니다.");
        }

        // 폴더가 없으면 생성
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFilename = file.getOriginalFilename();
        String savedFilename = System.currentTimeMillis() + "_" + originalFilename;

        Path filePath = uploadPath.resolve(savedFilename);
        file.transferTo(filePath.toFile());

        return savedFilename;
    }
}
