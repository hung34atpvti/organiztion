package com.nmhung.organization.domain.org.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nmhung.organization.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "ORG", uniqueConstraints = {@UniqueConstraint(name = "UNQ_ORG_NAME_1", columnNames = {"NAME"})})
@SequenceGenerator(name = "TABLE_SEQ", sequenceName = "SEQ_ORG_NAME", allocationSize = 1)
@JsonIgnoreProperties(value = { "hibernateLazyInitializer" })
public class Org extends BaseEntity {
    @Column(name = "NAME")
    @NotEmpty(message = "Organization Name {notBlank.message}")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ADDRESS")
    @NotEmpty(message = "Organization Address {notBlank.message}")
    private String address;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }
}
