package ru.otus.eshop.model.catalog;

import ru.otus.eshop.model.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Property extends NamedEntity {
    private PropertyType type;
}
