package ru.otus.eshop.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.catalog.ProductDescription;
import ru.otus.eshop.model.catalog.ProductDescriptionRepository;
import ru.otus.eshop.model.process.Cart;
import ru.otus.eshop.model.process.CartItem;
import ru.otus.eshop.model.process.CartRepository;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

@Service
@RequiredArgsConstructor
@Transactional
class CartService implements ICartService {
    private final @NonNull CartRepository cartRepository;
    private final @NonNull ProductDescriptionRepository productRepository;
    private final @NonNull IOrderService orderService;

    @Override
    public ProductDescription addToCart(long cartId, long productId, int qnt) {
        Cart cart = cartRepository.findOne(cartId);
        ProductDescription product = productRepository.findOne(productId);
        cart.addCartItem(CartItem.of(product, qnt));
        cartRepository.save(cart);
        return product;
    }

    @Override
    public ProductDescription removeFromCart(long cartId, Long productId) {
        Cart cart = cartRepository.findOne(cartId);
        ProductDescription product = productRepository.findOne(productId);
        cart.removeCartItem(CartItem.of(product));
        cartRepository.save(cart);
        return product;
    }

    @Override
    public Order checkout(long cartId, DeliveryInfo deliveryInfo) {
        Cart cart = cartRepository.findOne(cartId);
        Order order = orderService.createOrder(deliveryInfo, cart.getItems());
        clearCart(cart);
        return order;
    }

    @Override
    public Cart clearCart(long cartId) {
        Cart cart = cartRepository.findOne(cartId);
        clearCart(cart);
        return cart;
    }

    private Cart clearCart(Cart cart) {
        cart.clear();
        cartRepository.save(cart);
        return cart;
    }

}
