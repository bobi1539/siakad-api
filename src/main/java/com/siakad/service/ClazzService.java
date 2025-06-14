package com.siakad.service;

import com.siakad.entity.MClazz;
import com.siakad.model.dto.ClazzSearch;
import com.siakad.model.request.ClazzRequest;
import com.siakad.model.response.ClazzResponse;

public interface ClazzService extends CrudService<ClazzResponse, ClazzRequest, ClazzSearch, MClazz> {
}
