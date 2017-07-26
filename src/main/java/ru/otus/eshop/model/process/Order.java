package ru.otus.eshop.model.process;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import ru.otus.eshop.model.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Table(name = "ShopOrder")
public class Order extends BaseEntity {
    private OrderNumber number;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    @NotEmpty
    @ElementCollection
    private List<OrderItem> items;

    private Order(OrderNumber number) {
        this.number = number;
    }

    public Order(OrderNumber number, List<OrderItem> items) {
        this.number = number;
        this.items = items;
    }
    @NotEmpty
    public static Order of(Collection<CartItem> cartItems) {
        List<OrderItem> orderItems = cartItems.stream().map(OrderItem::of).collect(Collectors.toList());
        return new Order(OrderNumber.generateOrderNumber(), orderItems);
    }

    public BigDecimal getSum() {
        return items.stream().map(OrderItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void markCancelled() {
        if (status == OrderStatus.NEW) {
            throw new IllegalArgumentException("Can't be cancelled if not new");
        }
        status = OrderStatus.CANCELLED;
    }

    public void markDelivered() {
        if (status == OrderStatus.PAID) {
            throw new IllegalArgumentException("Can't be delivered if not paid");
        }
        status = OrderStatus.DELIVERED;
    }

    public void markPaid() {
        if (status == OrderStatus.PAID) {
            throw new IllegalArgumentException("Can't be paid if not new");
        }
        status = OrderStatus.DELIVERED;
    }
}
