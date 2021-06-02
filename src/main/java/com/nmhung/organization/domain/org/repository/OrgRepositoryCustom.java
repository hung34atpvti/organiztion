package com.nmhung.organization.domain.org.repository;

import com.nmhung.organization.domain.org.model.Org;
import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface OrgRepositoryCustom {
    Page<Org> search(Predicate predicate, Pageable pageable);
}
