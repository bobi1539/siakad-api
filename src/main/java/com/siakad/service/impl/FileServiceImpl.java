package com.siakad.service.impl;

import com.siakad.config.AppConfig;
import com.siakad.constant.Constant;
import com.siakad.constant.GlobalMessage;
import com.siakad.exception.BusinessException;
import com.siakad.model.response.FileResponse;
import com.siakad.service.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@AllArgsConstructor
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    public final AppConfig appConfig;

    @Override
    public FileResponse download(String fileName, String directory) {
        try {
            Path storagePath = Paths.get(appConfig.getPathFile() + directory);
            Path filePath = storagePath.resolve(fileName).normalize();

            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new BusinessException(GlobalMessage.FILE_DOESNT_EXIST);
            }

            String contentType = Files.probeContentType(filePath);

            return FileResponse.builder()
                    .resource(resource)
                    .mediaType(MediaType.parseMediaType(contentType))
                    .build();
        } catch (IOException e) {
            log.error(Constant.ERROR, e.getMessage());
            throw new BusinessException(GlobalMessage.FILE_DOESNT_EXIST);
        }
    }
}
