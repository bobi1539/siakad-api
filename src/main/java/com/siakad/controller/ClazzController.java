package com.siakad.controller;

import com.siakad.constant.Constant;
import com.siakad.constant.Endpoint;
import com.siakad.model.dto.ClazzSearch;
import com.siakad.model.dto.Header;
import com.siakad.model.request.ClazzRequest;
import com.siakad.model.response.ClazzResponse;
import com.siakad.service.ClazzService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Endpoint.CLAZZ)
@SecurityRequirement(name = Constant.AUTHORIZATION)
public class ClazzController extends AbstractBaseController {

    private final ClazzService clazzService;

    @GetMapping("/all")
    public List<ClazzResponse> findAll(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean isDeleted,
            @RequestParam(required = false) Integer gradeLevel,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        ClazzSearch clazzSearch = ClazzSearch.builder()
                .value(search)
                .isDeleted(isDeleted)
                .gradeLevel(gradeLevel)
                .page(0)
                .size(0)
                .build();
        return clazzService.findAll(clazzSearch, header);
    }

    @GetMapping
    public Page<ClazzResponse> findAllPagination(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean isDeleted,
            @RequestParam(required = false) Integer gradeLevel,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_PAGE) int page,
            @RequestParam(required = false, defaultValue = Constant.DEFAULT_SIZE) int size,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        ClazzSearch clazzSearch = ClazzSearch.builder()
                .value(search)
                .isDeleted(isDeleted)
                .gradeLevel(gradeLevel)
                .page(page)
                .size(size)
                .build();
        return clazzService.findAllPagination(clazzSearch, header);
    }

    @GetMapping("/{id}")
    public ClazzResponse findById(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return clazzService.findById(id, header);
    }

    @PostMapping
    public ClazzResponse create(
            @RequestBody @Valid ClazzRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return clazzService.create(request, header);
    }

    @PutMapping("/{id}")
    public ClazzResponse update(
            @PathVariable Long id,
            @RequestBody @Valid ClazzRequest request,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return clazzService.update(id, request, header);
    }

    @DeleteMapping("/{id}")
    public ClazzResponse delete(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return clazzService.delete(id, header);
    }

    @PutMapping("/restore/{id}")
    public ClazzResponse restore(
            @PathVariable Long id,
            @Parameter(hidden = true) @ModelAttribute Header header
    ) {
        return clazzService.restore(id, header);
    }
}
