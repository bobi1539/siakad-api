package com.siakad.service;

import com.siakad.model.response.FileResponse;

public interface FileService {

    FileResponse download(String fileName, String directory);
}
