package com.siakad.service;

import com.siakad.config.AppConfig;
import com.siakad.constant.Constant;
import com.siakad.constant.GlobalMessage;
import com.siakad.exception.BusinessException;
import com.siakad.helper.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@Slf4j
public abstract class AbstractFileService {

    protected AppConfig appConfig;
    protected Map<String, String> supportedFormat;
    protected long maxFileSize;

    protected AbstractFileService(AppConfig appConfig) {
        this.appConfig = appConfig;
        this.supportedFormat = initSupportedFormat();
        this.maxFileSize = initMaxFileSize();
    }

    protected abstract Map<String, String> initSupportedFormat();

    protected abstract long initMaxFileSize();

    protected String getExtension(String fileName) {
        if (StringHelper.isEmpty(fileName) || !fileName.contains(Constant.DOT)) {
            throw new BusinessException(GlobalMessage.FILE_NOT_VALID);
        }
        return fileName.substring(fileName.lastIndexOf(Constant.DOT) + 1).toLowerCase();
    }

    protected String generateFileName(String extension) {
        return String.format(
                "SIAKAD_%s_%d.%s",
                StringHelper.random(40),
                System.currentTimeMillis(),
                extension
        );
    }

    protected void validateFile(MultipartFile file) {
        String contentType = file.getContentType();
        String extensionAllowed = supportedFormat.get(contentType);
        String originalExtension = getExtension(file.getOriginalFilename());

        if (StringHelper.isEmpty(contentType) || StringHelper.isEmpty(extensionAllowed)) {
            throw new BusinessException(GlobalMessage.FILE_NOT_ALLOWED);
        }

        if (!originalExtension.equalsIgnoreCase(extensionAllowed)) {
            throw new BusinessException(GlobalMessage.FILE_NOT_ALLOWED);
        }

        if (file.getSize() > maxFileSize) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, String.format("Ukuran File Maksimal %sMB", maxMB()));
        }
    }

    protected String saveFile(MultipartFile file, String directory) {
        String contentType = file.getContentType();
        String extension = supportedFormat.get(contentType);
        String fileName = generateFileName(extension);

        Path storagePath = Paths.get(appConfig.getPathFile() + directory);
        Path filePath = storagePath.resolve(fileName);

        try {
            Files.createDirectories(storagePath);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File saved at : {}", filePath);
            return fileName;
        } catch (IOException e) {
            log.error("Failed to save file : {}", e.getMessage(), e);
            throw new BusinessException(GlobalMessage.INTERNAL_SERVER_ERROR);
        }
    }

    protected void deleteFile(String fileName, String directory) {
        String directoryPath = appConfig.getPathFile() + directory;
        Path filePath = Paths.get(directoryPath, fileName);

        try {
            Files.deleteIfExists(filePath);
            log.info("Successfully deleted file: {}", filePath);
        } catch (IOException e) {
            log.error("Failed to delete file: {}. Error: {}", filePath, e.getMessage(), e);
        }
    }

    private long maxMB() {
        return maxFileSize / (1024 * 1024);
    }
}
