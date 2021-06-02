package com.nmhung.organization.common.repository;

import java.util.List;

import com.nmhung.organization.common.model.BaseEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.querydsl.core.types.Predicate;


@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T>,
        DataTablesRepository<T, Long>, QuerydslPredicateExecutor<T> {

    T getById(Long id);

    List<T> findAll(Predicate predicate);

    List<T> findAll(Predicate predicate, Sort sort);

}