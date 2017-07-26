package ru.otus.eshop.model.process;

import lombok.Getter;
import ru.otus.eshop.model.catalog.Product;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Embeddable
public class OrderItem implements Serializable {
    @OneToOne
    private Product product;
    @Min(1)
    @Max(25)
    private int qnt;
    @NotNull
    private BigDecimal price;
    @NotNull
    private BigDecimal amount;

    OrderItem() {
    }

    public OrderItem(Product product, int qnt, BigDecimal price, BigDecimal amount) {
        this.product = product;
        this.qnt = qnt;
        this.price = price;
        this.amount = amount;
    }

    public static OrderItem of(CartItem cartItem) {
        return OrderItem.of(cartItem.getProduct(), cartItem.getQnt(), cartItem.getProduct().getPrice());
    }

    public static OrderItem of(Product product, int qnt, BigDecimal price) {
        BigDecimal amount = price.multiply(BigDecimal.valueOf(qnt));
        return new OrderItem(product, qnt, price, amount);
    }

}
