package com.siakad.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String save(MultipartFile file, String directory);

    void delete(String fileName, String directory);
}
