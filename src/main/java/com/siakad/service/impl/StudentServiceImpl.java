package com.siakad.service.impl;

import com.siakad.constant.Constant;
import com.siakad.entity.MClazz;
import com.siakad.entity.MStudent;
import com.siakad.helper.SpecificationHelper;
import com.siakad.model.dto.Header;
import com.siakad.model.dto.StudentSearch;
import com.siakad.model.request.StudentRequest;
import com.siakad.model.response.StudentResponse;
import com.siakad.repository.StudentRepository;
import com.siakad.service.AbstractMasterService;
import com.siakad.service.ClazzService;
import com.siakad.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentServiceImpl extends AbstractMasterService implements StudentService {

    private final StudentRepository studentRepository;
    private final ClazzService clazzService;

    @Override
    public List<StudentResponse> findAll(StudentSearch search, Header header) {
        Specification<MStudent> specification = getSpecification(search);
        Sort sort = sortByIdAsc();
        List<MStudent> students = studentRepository.findAll(specification, sort);
        return students.stream().map(this::toResponse).toList();
    }

    @Override
    public Page<StudentResponse> findAllPagination(StudentSearch search, Header header) {
        Specification<MStudent> specification = getSpecification(search);
        Pageable pageable = pageableSortByIsDeletedAndIdAsc(search);
        Page<MStudent> studentPage = studentRepository.findAll(specification, pageable);
        return studentPage.map(this::toResponse);
    }

    @Override
    public StudentResponse findById(Long id, Header header) {
        return toResponse(findByIdEntity(id, header));
    }

    @Override
    public StudentResponse create(StudentRequest request, Header header) {
        MStudent student = MStudent.builder().build();
        setStudentData(student, request, header);
        setDeleted(student, header);
        return toResponse(save(student));
    }

    @Override
    public StudentResponse update(Long id, StudentRequest request, Header header) {
        MStudent student = findByIdEntity(id, header);
        setStudentData(student, request, header);
        setUpdatedBy(student, header);
        return toResponse(save(student));
    }

    @Override
    public StudentResponse delete(Long id, Header header) {
        MStudent student = findByIdEntity(id, header);

        if (student.isDeleted()) {
            hardDelete(studentRepository, student);
            return toResponse(student);
        }

        softDelete(studentRepository, student, header);
        return toResponse(student);
    }

    @Override
    public StudentResponse restore(Long id, Header header) {
        MStudent student = findByIdEntity(id, header);
        restoreData(studentRepository, student, header);
        return toResponse(student);
    }

    @Override
    public MStudent findByIdEntity(Long id, Header header) {
        return studentRepository.findById(id).orElseThrow(notFoundException(Constant.STUDENT));
    }

    private StudentResponse toResponse(MStudent student) {
        return StudentResponse.build(student);
    }

    private Specification<MStudent> getSpecification(StudentSearch search) {
        Specification<MStudent> spec = SpecificationHelper.stringLike(MStudent.F_FULL_NAME, search.getValue());
        return spec
                .and(SpecificationHelper.objectEquals(MStudent.F_GENDER, search.getGender()))
                .and(SpecificationHelper.objectEquals(MStudent.F_STATUS, search.getStatus()))
                .and(getSpecificationIsDeleted(search.getIsDeleted()));
    }

    private void setStudentData(MStudent student, StudentRequest request, Header header) {
        student.setFullName(request.getFullName());
        student.setNisn(request.getNisn());
        student.setNis(request.getNis());
        student.setBirthDate(request.getBirthDate());
        student.setEmail(request.getEmail());
        student.setPhoneNumber(request.getPhoneNumber());
        student.setAddress(request.getAddress());
        student.setGender(request.getGender());
        student.setStatus(request.getStatus());

        MClazz currentClazz = clazzService.findByIdEntity(request.getCurrentClazzId(), header);
        student.setCurrentClazz(currentClazz);
    }

    private MStudent save(MStudent student) {
        return studentRepository.save(student);
    }
}
