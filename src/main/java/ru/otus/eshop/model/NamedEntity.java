package ru.otus.eshop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Getter
@MappedSuperclass
public class NamedEntity extends BaseEntity {
    @NotEmpty
    private @NonNull String name;

    public NamedEntity(String name) {
        this.name = name;
    }
}
