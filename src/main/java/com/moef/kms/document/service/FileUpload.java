package com.moef.kms.document.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FileUpload {

    // 절대경로로 설정하거나 서버 실행 경로 기준으로 설정
    private final String uploadDir = System.getProperty("user.dir") + "/uploads";

    public String upload(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("빈 파일입니다.");
        }

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
