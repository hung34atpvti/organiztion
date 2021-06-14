package com.nmhung.organization.domain.org.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nmhung.organization.common.model.BaseEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
@Data
@Table(name = "ORG", uniqueConstraints = {@UniqueConstraint(name = "UNQ_ORG_NAME_1", columnNames = {"NAME"})})
@SequenceGenerator(name = "TABLE_SEQ", sequenceName = "SEQ_ORG_NAME", allocationSize = 1)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
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
        final var prime = 31;
        var result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        var org = (Org) o;
        return Objects.equals(name, org.name) &&
                Objects.equals(description, org.description) &&
                Objects.equals(address, org.address);
    }
}
