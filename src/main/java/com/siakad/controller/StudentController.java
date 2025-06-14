package com.siakad.controller;

import com.siakad.constant.Constant;
import com.siakad.constant.Endpoint;
import com.siakad.constant.Gender;
import com.siakad.constant.StudentStatus;
import com.siakad.model.dto.Header;
import com.siakad.model.dto.StudentSearch;
import com.siakad.model.request.StudentRequest;
import com.siakad.model.response.StudentResponse;
import com.siakad.service.StudentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.STUDENT)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class StudentController extends AbstractBaseController {

    private final StudentService studentService;

    @GetMapping("/all")
    public List<StudentResponse> findAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean isDeleted,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) StudentStatus status,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        StudentSearch studentSearch = StudentSearch.builder()
                .value(search)
                .isDeleted(isDeleted)
                .gender(gender)
                .status(status)
                .page(0)
                .size(0)
                .build();
        return studentService.findAll(studentSearch, header);
    }

    @GetMapping
    public Page<StudentResponse> findAllPagination(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean isDeleted,
            @RequestParam(required = false) Gender gender,
            @RequestParam(required = false) StudentStatus status,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE) int page,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_SIZE) int size,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        StudentSearch studentSearch = StudentSearch.builder()
                .value(search)
                .isDeleted(isDeleted)
                .gender(gender)
                .status(status)
                .page(page)
                .size(size)
                .build();
        return studentService.findAllPagination(studentSearch, header);
    }

    @GetMapping("/{id}")
    public StudentResponse findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return studentService.findById(id, header);
    }

    @PostMapping
    public StudentResponse create(
            @RequestBody @Valid StudentRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return studentService.create(request, header);
    }

    @PutMapping("/{id}")
    public StudentResponse update(
            @PathVariable Long id,
            @RequestBody @Valid StudentRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return studentService.update(id, request, header);
    }

    @DeleteMapping("/{id}")
    public StudentResponse delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return studentService.delete(id, header);
    }

    @PutMapping("/restore/{id}")
    public StudentResponse restore(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return studentService.restore(id, header);
    }
}
