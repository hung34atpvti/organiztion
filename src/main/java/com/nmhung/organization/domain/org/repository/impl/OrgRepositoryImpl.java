package com.nmhung.organization.domain.org.repository.impl;

import com.nmhung.organization.domain.org.model.Org;
import com.nmhung.organization.domain.org.model.QOrg;
import com.nmhung.organization.domain.org.repository.OrgRepositoryCustom;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class OrgRepositoryImpl implements OrgRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Org> search(Predicate predicate, Pageable pageable) {
        var qOrg = QOrg.org;
        JPAQuery<Org> query = new JPAQuery<>(entityManager);
        query
                .from(qOrg)
                .where(predicate)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());
        PathBuilder<Org> entityPath = new PathBuilder<>(Org.class, "org");
        for (Sort.Order order : pageable.getSort()) {
            query.orderBy(new OrderSpecifier(Order.valueOf(order.getDirection().name()), entityPath.get(order.getProperty())));
        }
        return new PageImpl<>(query.fetch(), pageable, query.fetchCount());
    }

}
