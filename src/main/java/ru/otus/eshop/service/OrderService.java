package ru.otus.eshop.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.catalog.ProductDescription;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.OrderRepository;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
class OrderService implements IOrderService {
    private final @NonNull OrderRepository orderRepository;

    @Override
    public Order pay(long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.markPaid();
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order cancel(long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.markCancelled();
        orderRepository.save(order);
        return order;
    }

    @Override
    public Order createOrder(DeliveryInfo deliveryInfo, Map<ProductDescription, Integer> items) {
        Order order = Order.of(deliveryInfo);
        items.forEach(order::addItem);
        orderRepository.save(order);
        return order;
    }
}
