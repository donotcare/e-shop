package ru.otus.eshop.model.catalog;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;


@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class PropertyValue {
    @OneToOne
    private @NonNull Property property;
    private @NonNull String value;
}
