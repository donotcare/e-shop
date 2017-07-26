package ru.otus.eshop.model.catalog;

import ru.otus.eshop.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends BaseEntity {
    @OneToOne
    private ProductInfo info;
    private BigDecimal price;
}
