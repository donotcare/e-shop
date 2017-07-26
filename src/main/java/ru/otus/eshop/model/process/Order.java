package ru.otus.eshop.model.process;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@Setter
@Getter
@Table(name = "ShopOrder")
public class Order {
    @Id
    @GeneratedValue
    private long id;
    private OrderNumber number;

    @Enumerated
    private OrderStatus status = OrderStatus.NEW;

    @NotEmpty
    @ElementCollection
    private List<OrderItem> items = Lists.newArrayList();

    Order() {
    }

    private Order(OrderNumber number) {
        this.number = number;
    }

    public static Order of(OrderNumber number) {
        checkNotNull(number);
        return new Order(number);
    }

    public BigDecimal getSum() {
        return items.stream().map(OrderItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
