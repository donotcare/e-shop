package ru.otus.eshop.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.catalog.Product;
import ru.otus.eshop.model.catalog.ProductRepository;
import ru.otus.eshop.model.process.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderRepository shopOrderRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, OrderRepository shopOrderRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.shopOrderRepository = shopOrderRepository;
    }

    public Product addToCart(long cartId, Long productId, int qnt) {
        Cart cart = cartRepository.findOne(cartId);
        Product product = productRepository.findOne(productId);
        cart.addCartItem(CartItem.createItem(product, qnt));
        cartRepository.save(cart);
        return product;
    }

    public Product removeFromCart(long cartId, Long productId) {
        Cart cart = cartRepository.findOne(cartId);
        Product product = productRepository.findOne(productId);
        cart.removeCartItem(CartItem.createItem(product, 1));
        cartRepository.save(cart);
        return product;
    }

    public Order checkout(long cartId) {
        Cart cart = cartRepository.findOne(cartId);
        Order order = createShopOrder(cart);
        cart.clear();
        cartRepository.save(cart);
        shopOrderRepository.save(order);
        return order;
    }

    private Order createShopOrder(Cart cart) {
        Order order = Order.of(OrderNumber.generateOrderNumber());
        List<OrderItem> orderItems = cart.getItems().values().stream().map(OrderItem::of).collect(Collectors.toList());
        order.setItems(orderItems);
        return order;
    }
}
