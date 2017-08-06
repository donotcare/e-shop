package ru.otus.eshop.model.catalog;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Getter
@NoArgsConstructor
@Embeddable
public class ProductPrice {
    private @NotNull BigDecimal price;

    private ProductPrice(BigDecimal price) {
        this.price = price;
    }

    public static ProductPrice of(BigDecimal price) {
        return new ProductPrice(price);
    }
}
