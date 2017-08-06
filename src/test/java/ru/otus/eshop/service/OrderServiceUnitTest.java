package ru.otus.eshop.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.otus.eshop.model.catalog.ProductDescription;
import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.OrderRepository;
import ru.otus.eshop.model.process.OrderStatus;
import ru.otus.eshop.model.process.TestUtils;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {
    private IOrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Before
    public void setUp() {
        this.orderService = new OrderService(orderRepository);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectsNullOrderRepository(){
        new OrderService(null);
    }

    private final long ORDER_ID = 1L;

    @Test
    public void testOrderPay() {
        Order order = TestUtils.createExistingOrderWithStatus(OrderStatus.NEW);
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
        orderService.pay(ORDER_ID);
        assertThat(order.getStatus(), is(OrderStatus.PAID));
    }

    @Test
    public void testOrderCancel() {
        long orderId = 1L;
        Order order = TestUtils.createExistingOrderWithStatus(OrderStatus.NEW);
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
        orderService.cancel(ORDER_ID);
        assertThat(order.getStatus(), is(OrderStatus.CANCELLED));
    }

    @Test
    public void testCreateOrder() {
        DeliveryInfo delivery = mock(DeliveryInfo.class);
        Map<ProductDescription, Integer> products = new HashMap<>();
        products.put(mock(ProductDescription.class), 1);
        Order order = orderService.createOrder(delivery, products);
        assertThat(order, is(notNullValue()));
        assertThat(order.getDeliveryInfo(), is(notNullValue()));
        assertThat(order.getItems().size(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectionCancelNotNewOffer() {
        Order order = TestUtils.createExistingOrderWithStatus(OrderStatus.PAID);
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
        orderService.cancel(ORDER_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void rejectionPayAlreadyPayedOffer() {
        Order order = TestUtils.createExistingOrderWithStatus(OrderStatus.PAID);
        when(orderRepository.findOne(ORDER_ID)).thenReturn(order);
        orderService.pay(ORDER_ID);
    }
}
