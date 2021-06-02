package com.nmhung.organization.domain.org.repository;

import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.model.QOrg;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.stereotype.Repository;

@Repository
public interface OrgRepository extends
        JpaRepository<Org, Long>,
        QuerydslPredicateExecutor<Org>, QuerydslBinderCustomizer<QOrg>,
        OrgRepositoryCustom {
    @Override
    default void customize(final QuerydslBindings bindings, final QOrg root) {
        bindings.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
