package com.siakad.service.impl;

import com.siakad.constant.Constant;
import com.siakad.entity.MClazz;
import com.siakad.entity.MTeacher;
import com.siakad.helper.SpecificationHelper;
import com.siakad.model.dto.ClazzSearch;
import com.siakad.model.dto.Header;
import com.siakad.model.request.ClazzRequest;
import com.siakad.model.response.ClazzResponse;
import com.siakad.repository.ClazzRepository;
import com.siakad.service.AbstractMasterService;
import com.siakad.service.ClazzService;
import com.siakad.service.TeacherService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClazzServiceImpl extends AbstractMasterService implements ClazzService {

    private final ClazzRepository clazzRepository;
    private final TeacherService teacherService;

    @Override
    public List<ClazzResponse> findAll(ClazzSearch search, Header header) {
        Specification<MClazz> specification = getSpecification(search);
        Sort sort = sortByIsDeletedAndIdAsc();
        List<MClazz> clazzList = clazzRepository.findAll(specification, sort);
        return clazzList.stream().map(this::toResponse).toList();
    }

    @Override
    public Page<ClazzResponse> findAllPagination(ClazzSearch search, Header header) {
        Specification<MClazz> specification = getSpecification(search);
        Pageable pageable = pageableSortByIsDeletedAndIdAsc(search);
        Page<MClazz> clazzPage = clazzRepository.findAll(specification, pageable);
        return clazzPage.map(this::toResponse);
    }

    @Override
    public ClazzResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public ClazzResponse create(ClazzRequest request, Header header) {
        MClazz clazz = MClazz.builder().build();
        setClazzData(clazz, request, header);
        setDeleted(clazz, header);
        return toResponse(save(clazz));
    }

    @Override
    public ClazzResponse update(Long id, ClazzRequest request, Header header) {
        MClazz clazz = findByIdEntity(id, header);
        setClazzData(clazz, request, header);
        setUpdatedBy(clazz, header);
        return toResponse(save(clazz));
    }

    @Override
    public ClazzResponse delete(Long id, Header header) {
        MClazz clazz = findByIdEntity(id, header);

        if (clazz.isDeleted()) {
            hardDelete(clazzRepository, clazz);
            return toResponse(clazz);
        }

        softDelete(clazzRepository, clazz, header);
        return toResponse(save(clazz));
    }

    @Override
    public ClazzResponse restore(Long id, Header header) {
        MClazz clazz = findByIdEntity(id, header);
        clazz.setDeleted(false);
        setUpdatedBy(clazz, header);
        return toResponse(save(clazz));
    }

    @Override
    public MClazz findByIdEntity(Long id, Header header) {
        return clazzRepository.findById(id).orElseThrow(notFoundException(Constant.CLAZZ));
    }

    private ClazzResponse toResponse(MClazz clazz) {
        return ClazzResponse.build(clazz);
    }

    private Specification<MClazz> getSpecification(ClazzSearch search) {
        Specification<MClazz> spec = SpecificationHelper.stringLike(MClazz.FIELD_NAME, search.getValue());
        return spec
                .and(SpecificationHelper.objectEquals(MClazz.FIELD_GRADE_LEVEL, search.getGradeLevel()))
                .and(getSpecificationIsDeleted(search.getIsDeleted()));
    }

    private void setClazzData(MClazz clazz, ClazzRequest request, Header header) {
        clazz.setName(request.getName());
        clazz.setGradeLevel(request.getGradeLevel());

        MTeacher homeroomTeacher = teacherService.findByIdEntity(request.getHomeroomTeacherId(), header);
        clazz.setHomeroomTeacher(homeroomTeacher);
    }

    private MClazz save(MClazz clazz) {
        return clazzRepository.save(clazz);
    }
}
