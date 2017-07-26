package ru.otus.eshop.service;

import ru.otus.eshop.model.catalog.Product;
import ru.otus.eshop.model.process.Order;

public interface ICartService {
    Product addToCart(long cartId, Long productId, int qnt);

    Product removeFromCart(long cartId, Long productId);

    Order checkout(long cartId);
}
