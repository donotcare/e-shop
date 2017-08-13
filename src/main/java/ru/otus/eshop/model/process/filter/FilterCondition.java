package ru.otus.eshop.model.process.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.otus.eshop.model.catalog.Property;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class FilterCondition {
    private @NonNull Comparison comparison;
    @OneToOne
    private @NonNull Property property;
    @Transient
    private String currentValue;
}
