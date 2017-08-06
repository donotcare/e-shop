package ru.otus.eshop.model.process;

import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.otus.eshop.model.catalog.ProductDescription;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Embeddable
public class OrderItem implements Serializable {
    @OneToOne
    private ProductDescription productInfo;
    @Min(1)
    @Max(25)
    private int qnt;
    @NotNull
    private BigDecimal price;

    public OrderItem(ProductDescription productInfo, int qnt, BigDecimal price) {
        this.productInfo = productInfo;
        this.qnt = qnt;
        this.price = price;
    }

    public BigDecimal getAmount() {
        return price.multiply(BigDecimal.valueOf(qnt));
    }

    public static OrderItem of(ProductDescription product, int qnt) {
        return new OrderItem(product, qnt, product.getCurrentPriceValue());
    }

    public static OrderItem of(ProductDescription productInfo, int qnt, BigDecimal price) {
        return new OrderItem(productInfo, qnt, price);
    }


}
