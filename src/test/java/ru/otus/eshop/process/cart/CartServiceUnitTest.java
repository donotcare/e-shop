package ru.otus.eshop.process.cart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.catalog.product.ProductRepository;
import ru.otus.eshop.process.*;
import ru.otus.eshop.process.order.IOrderService;
import ru.otus.eshop.process.order.delivery.DeliveryInfo;
import ru.otus.eshop.process.order.Order;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceUnitTest {
    @Mock
    private CartRepository cartRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private IOrderService orderService;
    private ICartService cartService;
    private Cart cart;

    @Before
    public void setUp() {
        this.cartService = new CartService(cartRepository, productRepository, orderService);
        this.cart = new Cart();
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullCartRepository() {
        new CartService(null, productRepository, orderService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullProductRepository() {
        new CartService(cartRepository, null, orderService);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullOrderService() {
        new CartService(cartRepository, productRepository, null);
    }

    @Test
    public void testAddToCart() {
        long productId = 1L;
        int qnt = 1;

        when(cartRepository.getCurrentUserCart()).thenReturn(cart);
        when(productRepository.findOne(productId)).thenReturn(new Product());

        assertThat(cart.getItems().size(), is(0));
        cartService.addToCart(productId, qnt);
        assertThat(cart.getItems().size(), is(1));
    }

    @Test
    public void testRemoveFromCart() {
        Product product = TestUtils.createExistingProduct();
        cart.addCartItem(CartItem.of(product, 1));

        when(cartRepository.getCurrentUserCart()).thenReturn(cart);
        when(productRepository.findOne(product.getId())).thenReturn(product);

        assertThat(cart.getItems().size(), is(1));
        cartService.removeFromCart(product.getId());
        assertThat(cart.getItems().size(), is(0));
    }

    @Test
    public void testCheckout() {
        Product product = TestUtils.createExistingProduct();
        cart.addCartItem(CartItem.of(product, 1));
        DeliveryInfo deliveryInfo = mock(DeliveryInfo.class);

        when(cartRepository.getCurrentUserCart()).thenReturn(cart);
        when(orderService.createOrder(deliveryInfo, cart.getItems())).thenReturn(TestUtils.createExistingOrder());

        Order order = cartService.checkout(deliveryInfo);
        assertThat(order, is(notNullValue()));
        assertThat(cart.getItems().size(), is(0));
    }

    @Test
    public void testClearCart() {
        Product product = TestUtils.createExistingProduct();
        cart.addCartItem(CartItem.of(product, 1));

        when(cartRepository.getCurrentUserCart()).thenReturn(cart);

        assertThat(cart.getItems().size(), is(1));
        cartService.clearCart();
        assertThat(cart.getItems().size(), is(0));
    }
}