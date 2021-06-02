package com.nmhung.organization.domain.org.repository;

import com.nmhung.organization.domain.org.model.Org;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends JpaRepository<Org, Long>, OrgRepositoryCustom {
}
