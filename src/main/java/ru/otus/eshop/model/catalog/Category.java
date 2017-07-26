package ru.otus.eshop.model.catalog;

import ru.otus.eshop.model.NamedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends NamedEntity {
    @OneToMany
    private List<Product> products;
}
