package com.siakad.model.response;

import lombok.*;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileResponse {
    private Resource resource;
    private MediaType mediaType;
}
