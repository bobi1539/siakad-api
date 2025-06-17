package com.siakad.controller;

import com.siakad.constant.Constant;
import com.siakad.constant.Endpoint;
import com.siakad.model.response.FileResponse;
import com.siakad.service.FileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.FILE)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class FileController {

    private final FileService fileService;

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> download(
            @PathVariable String fileName,
            @RequestParam String directory
    ) {
        FileResponse response = fileService.download(fileName, directory);

        Resource resource = response.getResource();
        String contentDispositionValue = Constant.CONTENT_DISPOSITION + resource.getFilename();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDispositionValue)
                .contentType(response.getMediaType())
                .body(resource);
    }
}
