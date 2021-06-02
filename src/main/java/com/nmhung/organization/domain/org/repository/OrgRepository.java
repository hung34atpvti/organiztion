package com.nmhung.organization.domain.org.repository;

import com.nmhung.organization.common.repository.BaseRepository;
import com.nmhung.organization.domain.org.model.Org;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends BaseRepository<Org>, OrgRepositoryCustom {
}
