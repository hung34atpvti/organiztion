package com.nmhung.organization.domain.org.service;

import com.nmhung.organization.domain.org.model.Org;

import java.util.List;

public interface OrgService {
    List<Org> findAll();

    int countAll();

    Org create(Org org);

}
