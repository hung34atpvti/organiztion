package com.nmhung.organization.domain.org.service;

import com.nmhung.organization.domain.org.model.Org;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface OrgService {
    Page<Org> findAll(Predicate predicate, Pageable pageable);

    Org create(Org org);

    Org findById(Long id);

    Org update(Long id, Org org);

    Org softDelete(Long id);

    boolean hardDelete(Long id);

}
