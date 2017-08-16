package ru.otus.eshop.process.order;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.process.order.delivery.DeliveryInfo;

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
    public Order createOrder(DeliveryInfo deliveryInfo, Map<Product, Integer> items) {
        Order order = Order.of(deliveryInfo);
        items.forEach(order::addItem);
        orderRepository.save(order);
        return order;
    }
}
