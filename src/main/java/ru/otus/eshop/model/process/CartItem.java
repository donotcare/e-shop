package ru.otus.eshop.model.process;

import ru.otus.eshop.model.catalog.Product;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Embeddable
public class CartItem implements Serializable {
    @OneToOne
    private Product product;
    @Min(1)
    @Max(25)
    private int qnt;

    CartItem() {
    }

    private CartItem(Product product, int qnt) {
        this.product = product;
        this.qnt = qnt;
    }

    public static CartItem createItem(Product product, int qnt) {
        return new CartItem(product, qnt);
    }

    public BigDecimal getAmount() {
        return product.getPrice().multiply(BigDecimal.valueOf(qnt));
    }
}
