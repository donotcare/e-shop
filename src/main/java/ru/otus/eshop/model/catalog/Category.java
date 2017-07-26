package ru.otus.eshop.model.catalog;

import lombok.*;
import ru.otus.eshop.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
public class Category extends NamedEntity {
    @OneToMany
    private @NonNull List<Product> products;

    Category() {
        super(null);
    }

    public Category(String name, List<Product> products) {
        super(name);
        this.products = products;
    }
}
