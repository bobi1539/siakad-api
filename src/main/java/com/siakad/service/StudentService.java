package com.siakad.service;

import com.siakad.entity.MStudent;
import com.siakad.model.dto.StudentSearch;
import com.siakad.model.request.StudentRequest;
import com.siakad.model.response.StudentResponse;

public interface StudentService extends CrudService<StudentResponse, StudentRequest, StudentSearch, MStudent> {
}
