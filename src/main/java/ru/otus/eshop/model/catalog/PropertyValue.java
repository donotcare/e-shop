package ru.otus.eshop.model.catalog;

import lombok.*;
import ru.otus.eshop.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
public class PropertyValue extends BaseEntity {
    @OneToOne
    private @NonNull Property property;
    private @NonNull String value;
}
