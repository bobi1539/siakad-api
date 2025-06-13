package com.siakad.model.response;

import lombok.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileResponse {
    private String fileName;
    private InputStreamResource resource;
    private MediaType mediaType;
}
