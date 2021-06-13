package com.nmhung.organization.domain.org.service.impl;

import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.model.QOrg;
import com.nmhung.organization.domain.org.repository.OrgRepository;
import com.nmhung.organization.domain.org.service.OrgService;
import com.nmhung.organization.exceptions.ErrorMessage;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.Objects;

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
    public Org create(Org org) {
        if (this.isExisted(org.getName())){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessage.DUPLICATE_ORG_NAME);
        }
        org.setCreatedDate(LocalDateTime.now());
        org.setHashcode(org.hashCode());
        return repository.save(org);
    }

    @Override
    public Org findById(Long id) {
        var result = repository.findById(id).orElse(null);
        if (Objects.isNull(result)) {
           this.throwIfNotExisted();
        }
        return result;
    }

    @Override
    public Org update(Long id, Org org) {
        this.throwIfNotExisted(id);
        org.setId(id);
        return repository.save(org);
    }

    @Override
    public Org softDelete(Long id) {
        var org = this.findById(id);
        org.setId(id);
        org.setActiveInd(false);
        return repository.save(org);
    }

    @Override
    public boolean hardDelete(Long id) {
        this.throwIfNotExisted(id);
        repository.deleteById(id);
        return !this.isExisted(id);
    }

    private boolean isExisted(Long id) {
        return Objects.equals(repository.count(QOrg.org.id.eq(id)), 1L);
    }

    private boolean isExisted(String orgName) {
       return Objects.equals(repository.count(QOrg.org.name.equalsIgnoreCase(orgName)), 1L);
    }

    private void throwIfNotExisted() {
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessage.ORG_NOT_FOUND);
    }

    private void throwIfNotExisted(Long id) {
        if (this.isExisted(id)) {
            return;
        }
        throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, ErrorMessage.ORG_NOT_FOUND);
    }

}
