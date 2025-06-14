package com.siakad.controller;

import com.siakad.constant.Constant;
import com.siakad.constant.Endpoint;
import com.siakad.model.dto.Header;
import com.siakad.model.dto.Search;
import com.siakad.model.request.TeacherRequest;
import com.siakad.model.response.TeacherResponse;
import com.siakad.service.TeacherService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.TEACHER)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/all")
    public List<TeacherResponse> findAll(
            @ParameterObject @ModelAttribute Search search,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return teacherService.findAll(search, header);
    }

    @GetMapping
    public Page<TeacherResponse> findAllPagination(
            @ParameterObject @ModelAttribute Search search,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return teacherService.findAllPagination(search, header);
    }

    @GetMapping("/{id}")
    public TeacherResponse findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return teacherService.findById(id, header);
    }

    @PostMapping
    public TeacherResponse create(
            @RequestBody @Valid TeacherRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return teacherService.create(request, header);
    }

    @PutMapping("/{id}")
    public TeacherResponse update(
            @PathVariable Long id,
            @RequestBody @Valid TeacherRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return teacherService.update(id, request, header);
    }

    @DeleteMapping("/{id}")
    public TeacherResponse delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return teacherService.delete(id, header);
    }

    @PutMapping("/restore/{id}")
    public TeacherResponse restore(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return teacherService.restore(id, header);
    }
}
