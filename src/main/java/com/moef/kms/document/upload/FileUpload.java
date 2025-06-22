package com.moef.kms.document.upload;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * 파일 업로드 유틸리티
 */
@Component
public class FileUpload {

    private static final String UPLOAD_DIR = "/path/to/uploads/";

    /**
     * MultipartFile을 지정된 디렉터리에 저장하고,
     * 저장된 파일명을 반환합니다.
     */
    public String upload(MultipartFile file) throws IOException {
        String original = file.getOriginalFilename();
        String ext = "";
        if (original != null && original.contains(".")) {
            ext = original.substring(original.lastIndexOf("."));
        }
        String filename = UUID.randomUUID().toString() + ext;
        Path target = Paths.get(UPLOAD_DIR).resolve(filename);
        Files.createDirectories(target.getParent());
        Files.copy(file.getInputStream(), target);
        return filename;
    }
}
