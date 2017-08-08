package ru.otus.eshop.model.process;

import com.google.common.base.Preconditions;
import lombok.*;
import ru.otus.eshop.model.catalog.Product;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@RequiredArgsConstructor
@Getter
@Embeddable
public class CartItem implements Serializable {
    @OneToOne
    private @NonNull
    Product product;
    @Min(0)
    @Max(25)
    private final int qnt;
    public BigDecimal getAmount() {
        return product.getCurrentPriceValue().multiply(BigDecimal.valueOf(qnt));
    }

    public static CartItem of(Product product, int qnt) {
        Preconditions.checkArgument(qnt > 0);
        return new CartItem(product, qnt);
    }
    public static CartItem of(Product product) {
        return new CartItem(product, 1);
    }

}
