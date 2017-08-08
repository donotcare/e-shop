package ru.otus.eshop.service;

import ru.otus.eshop.model.catalog.Product;
import ru.otus.eshop.model.process.Cart;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

public interface ICartService {
    Product addToCart(long productId, int qnt);

    Product removeFromCart(long productId);

    Order checkout(DeliveryInfo deliveryInfo);

    Cart clearCart();
}
