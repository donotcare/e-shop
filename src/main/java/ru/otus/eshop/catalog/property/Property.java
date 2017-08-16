package ru.otus.eshop.catalog.property;

import lombok.*;
import ru.otus.eshop.BaseEntity;
import ru.otus.eshop.catalog.category.Category;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Property extends BaseEntity {
    private @NonNull String name;
    @Enumerated(EnumType.STRING)
    private @NonNull
    PropertyType type;
    @ManyToOne
    private @NonNull
    Category category;
}
