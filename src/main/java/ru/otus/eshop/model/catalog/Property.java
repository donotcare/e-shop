package ru.otus.eshop.model.catalog;

import lombok.*;
import ru.otus.eshop.model.BaseEntity;

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
    private @NonNull PropertyType type;
    @ManyToOne
    private @NonNull Category category;
}
