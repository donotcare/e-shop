package ru.otus.eshop.service;

import ru.otus.eshop.model.catalog.ProductDescription;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

import java.util.Map;

public interface IOrderService {
    Order pay(long orderId);

    Order cancel(long orderId);

    Order createOrder(DeliveryInfo deliveryInfo, Map<ProductDescription, Integer> items);
}
