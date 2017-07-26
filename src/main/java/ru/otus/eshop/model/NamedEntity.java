package ru.otus.eshop.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@ToString
@MappedSuperclass
public class NamedEntity extends BaseEntity {
    @NotEmpty
    private String name;
}
