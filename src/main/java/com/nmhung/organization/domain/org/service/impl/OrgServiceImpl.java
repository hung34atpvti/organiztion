package com.nmhung.organization.domain.org.service.impl;

import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.repository.OrgRepository;
import com.nmhung.organization.domain.org.service.OrgService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgRepository repository;

    @Override
    public Page<Org> findAll(Predicate predicate, Pageable pageable) {
        var builder = new BooleanBuilder();
        return repository.search(builder.and(predicate), pageable);
    }

    @Override
    public int countAll() {
        return (int) repository.count();
    }

    @Override
    public Org create(Org org) {
        org.setCreatedDate(LocalDateTime.now());
        org.setHashcode(org.hashCode());
        return repository.save(org);
    }
}
