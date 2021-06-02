package com.nmhung.organization.domain.org.service;

import com.nmhung.organization.domain.org.model.Org;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrgService {
    Page<Org> findAll(Predicate predicate, Pageable pageable);

    int countAll();

    Org create(Org org);

}
