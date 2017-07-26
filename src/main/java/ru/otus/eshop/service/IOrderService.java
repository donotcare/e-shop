package ru.otus.eshop.service;

import ru.otus.eshop.model.process.Order;

public interface IOrderService {
    Order pay(long orderId);

    Order cancel(long orderId);
}
