package ru.otus.eshop.process.order;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

import static com.google.common.base.Preconditions.checkArgument;

@EqualsAndHashCode
@Getter
@ToString
public class OrderNumber implements Serializable {
    private final long value;

    private OrderNumber(long value) {
        this.value = value;
    }

    public static OrderNumber of(long value) {
        checkArgument(value > 0);
        return new OrderNumber(value);
    }

    public static OrderNumber generateOrderNumber() {
        return OrderNumber.of(System.nanoTime() % 100000);
    }
}
