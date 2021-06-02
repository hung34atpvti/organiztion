package com.nmhung.organization.domain.org.service.impl;

import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.repository.OrgRepository;
import com.nmhung.organization.domain.org.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrgServiceImpl implements OrgService {

    @Autowired
    private OrgRepository repository;

    @Override
    public List<Org> findAll() {
        return repository.findAll();
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
