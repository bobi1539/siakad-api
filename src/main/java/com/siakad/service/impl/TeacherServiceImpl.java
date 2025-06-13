package com.siakad.service.impl;

import com.siakad.constant.Constant;
import com.siakad.entity.MTeacher;
import com.siakad.helper.SpecificationHelper;
import com.siakad.model.dto.Header;
import com.siakad.model.dto.Search;
import com.siakad.model.request.TeacherRequest;
import com.siakad.model.response.TeacherResponse;
import com.siakad.repository.TeacherRepository;
import com.siakad.service.AbstractMasterService;
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
public class TeacherServiceImpl extends AbstractMasterService implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public List<TeacherResponse> findAll(Search search, Header header) {
        Specification<MTeacher> specification = getSpecification(search);
        Sort sort = sortByIsDeletedAndIdAsc();
        List<MTeacher> teachers = teacherRepository.findAll(specification, sort);
        return teachers.stream().map(this::toResponse).toList();
    }

    @Override
    public Page<TeacherResponse> findAllPagination(Search search, Header header) {
        Specification<MTeacher> specification = getSpecification(search);
        Pageable pageable = pageableSortByIsDeletedAndIdAsc(search);
        Page<MTeacher> teacherPage = teacherRepository.findAll(specification, pageable);
        return teacherPage.map(this::toResponse);
    }

    @Override
    public TeacherResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public TeacherResponse create(TeacherRequest request, Header header) {
        MTeacher teacher = MTeacher.builder().build();
        teacher.setName(request.getName());
        setDeleted(teacher, header);
        return toResponse(save(teacher));
    }

    @Override
    public TeacherResponse update(Long id, TeacherRequest request, Header header) {
        MTeacher teacher = findByIdEntity(id, header);
        teacher.setName(request.getName());
        setUpdatedBy(teacher, header);
        return toResponse(save(teacher));
    }

    @Override
    public TeacherResponse delete(Long id, Header header) {
        MTeacher teacher = findByIdEntity(id, header);

        if (teacher.isDeleted()) {
            hardDelete(teacherRepository, teacher);
            return toResponse(teacher);
        }

        softDelete(teacherRepository, teacher, header);
        return toResponse(teacher);
    }

    @Override
    public TeacherResponse restore(Long id, Header header) {
        MTeacher teacher = findByIdEntity(id, header);
        teacher.setDeleted(false);
        setUpdatedBy(teacher, header);
        return toResponse(save(teacher));
    }

    @Override
    public MTeacher findByIdEntity(Long id, Header header) {
        return teacherRepository.findById(id).orElseThrow(notFoundException(Constant.TEACHER));
    }

    private TeacherResponse toResponse(MTeacher teacher) {
        return TeacherResponse.build(teacher);
    }

    private Specification<MTeacher> getSpecification(Search search) {
        Specification<MTeacher> spec = SpecificationHelper.stringLike(MTeacher.FIELD_NAME, search.getValue());
        return spec.and(getSpecificationIsDeleted(search.getIsDeleted()));
    }

    private MTeacher save(MTeacher teacher) {
        return teacherRepository.save(teacher);
    }
}
