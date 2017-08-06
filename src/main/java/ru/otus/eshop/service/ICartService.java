package ru.otus.eshop.service;

import ru.otus.eshop.model.catalog.ProductDescription;
import ru.otus.eshop.model.process.Cart;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

public interface ICartService {
    ProductDescription addToCart(long cartId, long productId, int qnt);

    ProductDescription removeFromCart(long cartId, Long productId);

    Order checkout(long cartId, DeliveryInfo deliveryInfo);

    Cart clearCart(long cartId);
}
