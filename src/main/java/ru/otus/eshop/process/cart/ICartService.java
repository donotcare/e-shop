package ru.otus.eshop.process.cart;

import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.process.order.Order;
import ru.otus.eshop.process.order.delivery.DeliveryInfo;

public interface ICartService {
    Product addToCart(long productId, int qnt);

    Product removeFromCart(long productId);

    Order checkout(DeliveryInfo deliveryInfo);

    Cart clearCart();
}
