package com.siakad.service;

import com.siakad.entity.MTeacher;
import com.siakad.model.dto.Search;
import com.siakad.model.request.TeacherRequest;
import com.siakad.model.response.GetDirectoryResponse;
import com.siakad.model.response.TeacherResponse;

public interface TeacherService extends CrudService<TeacherResponse, TeacherRequest, Search, MTeacher> {

    GetDirectoryResponse getDirectory();
}
