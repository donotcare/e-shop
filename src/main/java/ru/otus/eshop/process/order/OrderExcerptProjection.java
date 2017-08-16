package ru.otus.eshop.process.order;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = Order.class)
interface OrderExcerptProjection {
    @Value("#{target.number.value}")
    String getNumber();
    String getAmount();
}
