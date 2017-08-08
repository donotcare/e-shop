package ru.otus.eshop.model.catalog;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.otus.eshop.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Category extends BaseEntity {
    private @NonNull String name;
    @OneToMany
    private @NonNull List<Product> products;

}
