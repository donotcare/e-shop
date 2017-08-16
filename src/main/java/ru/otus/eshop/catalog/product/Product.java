package ru.otus.eshop.catalog.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.otus.eshop.BaseEntity;
import ru.otus.eshop.catalog.category.Category;
import ru.otus.eshop.catalog.property.PropertyValue;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Entity

public class Product extends BaseEntity {
    private @NonNull String name;
    @ManyToOne(fetch = FetchType.LAZY)
    private @NonNull
    Category category;
    @ElementCollection
    private @NonNull Set<PropertyValue> properties;
    private @NonNull
    ProductPrice curentPrice;
    @JsonIgnore
    private int availableAmount;

    public BigDecimal getCurrentPriceValue() {
        return curentPrice.getPrice();
    }

    public boolean isAvailable() {
        return availableAmount > 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }
}
