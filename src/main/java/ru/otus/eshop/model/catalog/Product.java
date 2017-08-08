package ru.otus.eshop.model.catalog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.otus.eshop.model.BaseEntity;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Product extends BaseEntity {
    private @NonNull String model;

    @ElementCollection
    private @NonNull Set<PropertyValue> properties;
    private @NonNull ProductPrice curentPrice;
    @JsonIgnore
    private int availableAmount;

    public BigDecimal getCurrentPriceValue() {
        return curentPrice.getPrice();
    }

    public boolean isAvailable() {
        return availableAmount > 0;
    }
}
