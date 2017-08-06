package ru.otus.eshop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.otus.eshop.model.catalog.ProductDescription;
import ru.otus.eshop.model.catalog.ProductDescriptionRepository;
import ru.otus.eshop.model.process.*;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

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
    private ProductDescriptionRepository productRepository;
    @Mock
    private IOrderService orderService;

    private ICartService cartService;
    private Cart cart;
    private final long CART_ID = 1L;

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

        when(cartRepository.findOne(CART_ID)).thenReturn(cart);
        when(productRepository.findOne(productId)).thenReturn(new ProductDescription());

        assertThat(cart.getItems().size(), is(0));
        cartService.addToCart(CART_ID, productId, qnt);
        assertThat(cart.getItems().size(), is(1));
    }

    @Test
    public void testRemoveFromCart() {
        ProductDescription product = TestUtils.createExistingProduct();
        cart.addCartItem(CartItem.of(product, 1));

        when(cartRepository.findOne(CART_ID)).thenReturn(cart);
        when(productRepository.findOne(product.getId())).thenReturn(product);

        assertThat(cart.getItems().size(), is(1));
        cartService.removeFromCart(CART_ID, product.getId());
        assertThat(cart.getItems().size(), is(0));
    }

    @Test
    public void testCheckout() {
        ProductDescription product = TestUtils.createExistingProduct();
        cart.addCartItem(CartItem.of(product, 1));
        DeliveryInfo deliveryInfo = mock(DeliveryInfo.class);

        when(cartRepository.findOne(CART_ID)).thenReturn(cart);
        when(orderService.createOrder(deliveryInfo, cart.getItems())).thenReturn(TestUtils.createExistingOrder());

        Order order = cartService.checkout(CART_ID, deliveryInfo);
        assertThat(order, is(notNullValue()));
        assertThat(cart.getItems().size(), is(0));
    }

    @Test
    public void testClearCart() {
        ProductDescription product = TestUtils.createExistingProduct();
        cart.addCartItem(CartItem.of(product, 1));

        when(cartRepository.findOne(CART_ID)).thenReturn(cart);

        assertThat(cart.getItems().size(), is(1));
        cartService.clearCart(CART_ID);
        assertThat(cart.getItems().size(), is(0));
    }
}