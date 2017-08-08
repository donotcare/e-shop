package ru.otus.eshop.model.process;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.NotEmpty;
import ru.otus.eshop.model.BaseEntity;
import ru.otus.eshop.model.catalog.Product;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Table(name = "ShopOrder")
public class Order extends BaseEntity {
    private OrderNumber number;
    private @NonNull DeliveryInfo deliveryInfo;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.NEW;

    @NotEmpty
    @ElementCollection
    private List<OrderItem> items = new ArrayList<>();

    private Order(OrderNumber number) {
        this.number = number;
    }

    private Order(OrderNumber number, DeliveryInfo deliveryInfo) {
        this.number = number;
        this.deliveryInfo = deliveryInfo;
    }

    public static Order of(DeliveryInfo deliveryInfo) {
        checkNotNull(deliveryInfo);
        return new Order(OrderNumber.generateOrderNumber(), deliveryInfo);
    }

    public void addItem(Product product, int qnt) {
        items.add(OrderItem.of(product, qnt));
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getAmount() {
        return items.stream().map(OrderItem::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void markCancelled() {
        if (status != OrderStatus.NEW) {
            throw new IllegalArgumentException("Can't be cancelled if not new");
        }
        status = OrderStatus.CANCELLED;
    }

    public void markDelivered() {
        if (status != OrderStatus.PAID) {
            throw new IllegalArgumentException("Can't be delivered if not paid");
        }
        status = OrderStatus.DELIVERED;
    }

    public void markPaid() {
        if (status != OrderStatus.NEW) {
            throw new IllegalArgumentException("Can't be paid if not new");
        }
        status = OrderStatus.PAID;
    }
}
