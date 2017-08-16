package ru.otus.eshop.process.cart;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.catalog.product.ProductRepository;
import ru.otus.eshop.process.order.Order;
import ru.otus.eshop.process.order.delivery.DeliveryInfo;
import ru.otus.eshop.security.SecurityUtil;
import ru.otus.eshop.process.order.IOrderService;

@Service
@RequiredArgsConstructor
@Transactional
class CartService implements ICartService {
    private final @NonNull CartRepository cartRepository;
    private final @NonNull
    ProductRepository productRepository;
    private final @NonNull
    IOrderService orderService;

    @Override
    public Product addToCart(long productId, int qnt) {
        String login = SecurityUtil.getCurrentUserLogin();
        Cart cart = cartRepository.getCurrentUserCart();
        Product product = productRepository.findOne(productId);
        cart.addCartItem(CartItem.of(product, qnt));
        cartRepository.save(cart);
        return product;
    }

    @Override
    public Product removeFromCart(long productId) {
        Cart cart = cartRepository.getCurrentUserCart();
        Product product = productRepository.findOne(productId);
        cart.removeCartItem(CartItem.of(product));
        cartRepository.save(cart);
        return product;
    }

    @Override
    public Order checkout(DeliveryInfo deliveryInfo) {
        Cart cart = cartRepository.getCurrentUserCart();
        Order order = orderService.createOrder(deliveryInfo, cart.getItems());
        clearCart(cart);
        return order;
    }

    @Override
    public Cart clearCart() {
        Cart cart = cartRepository.getCurrentUserCart();
        clearCart(cart);
        return cart;
    }

    private Cart clearCart(Cart cart) {
        cart.clear();
        cartRepository.save(cart);
        return cart;
    }
}
