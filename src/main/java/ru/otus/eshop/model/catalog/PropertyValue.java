package ru.otus.eshop.model.catalog;

import ru.otus.eshop.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class PropertyValue extends BaseEntity {

    @OneToOne
    private Property property;
    private String value;
}
