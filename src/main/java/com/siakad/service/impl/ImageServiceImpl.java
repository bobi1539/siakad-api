package com.siakad.service.impl;

import com.siakad.config.AppConfig;
import com.siakad.service.AbstractFileService;
import com.siakad.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class ImageServiceImpl extends AbstractFileService implements ImageService {

    public ImageServiceImpl(AppConfig appConfig) {
        super(appConfig);
    }

    @Override
    protected Map<String, String> initSupportedFormat() {
        return Map.of(
                "image/jpeg", "jpeg",
                "image/jpg", "jpg",
                "image/png", "png",
                "image/x-png", "png",
                "image/bmp", "bmp",
                "image/gif", "gif",
                "image/webp", "webp"
        );
    }

    @Override
    protected long initMaxFileSize() {
        return 1_024L * 1_024L;
    }

    @Override
    public String save(MultipartFile file, String directory) {
        return saveFile(file, directory);
    }

    @Override
    public void delete(String fileName, String directory) {
        deleteFile(fileName, directory);
    }
}
