package com.siakad.service;

import com.siakad.model.dto.Header;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudService<Response, Request, Search, Entity> {

    List<Response> findAll(Search search, Header header);

    Page<Response> findAllPagination(Search search, Header header);

    Response findById(Long id, Header header);

    Response create(Request request, Header header);

    Response update(Long id, Request request, Header header);

    Response delete(Long id, Header header);

    Response restore(Long id, Header header);

    Entity findByIdEntity(Long id, Header header);
}
