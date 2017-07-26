package ru.otus.eshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.OrderRepository;

@Service
@Transactional
class OrderService implements IOrderService {
    private final OrderRepository shopOrderRepository;

    public OrderService(OrderRepository shopOrderRepository) {
        this.shopOrderRepository = shopOrderRepository;
    }

    public Order pay(long orderId) {
        Order order = shopOrderRepository.findOne(orderId);
        order.markPaid();
        shopOrderRepository.save(order);
        return order;
    }

    @Override
    public Order cancel(long orderId) {
        Order order = shopOrderRepository.findOne(orderId);
        order.markCancelled();
        shopOrderRepository.save(order);
        return order;
    }
}
