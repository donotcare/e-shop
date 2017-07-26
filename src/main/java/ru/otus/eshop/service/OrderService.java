package ru.otus.eshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.OrderRepository;
import ru.otus.eshop.model.process.OrderStatus;

@Service
@Transactional
public class OrderService implements IOrderService {
    private final OrderRepository shopOrderRepository;

    public OrderService(OrderRepository shopOrderRepository) {
        this.shopOrderRepository = shopOrderRepository;
    }

    public Order pay(long orderId) {
        Order order = shopOrderRepository.findOne(orderId);
        order.setStatus(OrderStatus.PAIED);
        shopOrderRepository.save(order);
        return order;
    }

    @Override
    public Order cancel(long orderId) {
        Order order = shopOrderRepository.findOne(orderId);
        order.setStatus(OrderStatus.CANCELLED);
        shopOrderRepository.save(order);
        return order;
    }
}
