package ru.otus.eshop.process.order;

import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.process.order.delivery.DeliveryInfo;

import java.util.Map;

public interface IOrderService {
    Order pay(long orderId);

    Order cancel(long orderId);

    Order createOrder(DeliveryInfo deliveryInfo, Map<Product, Integer> items);
}
