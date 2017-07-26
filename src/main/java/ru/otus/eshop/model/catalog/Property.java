package ru.otus.eshop.model.catalog;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import ru.otus.eshop.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
public class Property extends NamedEntity {
    @Enumerated(EnumType.STRING)
    private @NonNull PropertyType type;

    Property() {
        super(null);
    }

    public Property(String name, PropertyType type) {
        super(name);
        this.type = type;
    }
}
