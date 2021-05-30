package com.nmhung.organization.common.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners({ AuditingEntityListener.class })
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "TABLE_SEQ")
    @Column(name = "ID", unique = true, nullable = false)
    protected Long id;

    @Column(name = "HASHCODE")
    protected Integer hashcode;

    @Column(name = "ACTIVE_IND")
    protected Boolean activeInd = Boolean.TRUE;

    @CreatedBy
    @Column(name = "CREATED_BY", insertable = true, updatable = false)
    protected String createdBy;

    @CreatedDate
    @Column(name = "CREATED_DATE", insertable = true, updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "UPDATED_BY", insertable = false, updatable = true)
    protected String updatedBy;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE", insertable = false, updatable = true)
    protected LocalDateTime updatedDate;

    @Version
    @Column(name = "VERSION")
    protected Long version;
}
