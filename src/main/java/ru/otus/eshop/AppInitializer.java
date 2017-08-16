package ru.otus.eshop;

import com.google.common.collect.ImmutableSet;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.otus.eshop.catalog.category.Category;
import ru.otus.eshop.catalog.category.ICategoryService;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.catalog.product.ProductPrice;
import ru.otus.eshop.catalog.product.ProductRepository;
import ru.otus.eshop.catalog.property.IPropertyService;
import ru.otus.eshop.catalog.property.Property;
import ru.otus.eshop.catalog.property.PropertyType;
import ru.otus.eshop.catalog.property.PropertyValue;
import ru.otus.eshop.process.order.Order;
import ru.otus.eshop.process.order.OrderRepository;
import ru.otus.eshop.process.order.delivery.DeliveryInfo;
import ru.otus.eshop.process.registration.IRegistrationService;
import ru.otus.eshop.security.SecurityUtil;
import ru.otus.eshop.system.UserRegistration;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class AppInitializer {
    private final ProductRepository productInfoRepository;
    private final ICategoryService categoryService;
    private final IPropertyService propertyService;
    private final IRegistrationService registrationService;
    private final OrderRepository orderRepository;


    @EventListener
    public void init(ApplicationReadyEvent event) {
        UserRegistration userRegistration = new UserRegistration("user", "user", "user");
        registrationService.registerNewUser(userRegistration);

        SecurityUtil.runAs("system", "system", "ROLE_ADMIN");
        Category category = new Category("Телевизоры");
        categoryService.save(category);

        Property type = new Property("Тип", PropertyType.STRING, category);
        propertyService.save(type);
        Property diagonal = new Property("Диагональ", PropertyType.STRING, category);
        propertyService.save(diagonal);

        PropertyValue productType = new PropertyValue(type, "ЖК");
        PropertyValue productDiagonal = new PropertyValue(type, "19 \"");
        ProductPrice productPrice = ProductPrice.of(new BigDecimal("25000.00"));
        Product product1 = new Product("LED Телевизор Samsung UE-19H4000", category, ImmutableSet.of(productDiagonal, productType), productPrice);

        productInfoRepository.save(product1);

        PropertyValue product2Type = new PropertyValue(type, "ЖК");
        PropertyValue product2Diagonal = new PropertyValue(type, "17 \"");
        ProductPrice productPrice2 = ProductPrice.of(new BigDecimal("40000.00"));
        Product product = new Product("LED Телевизор Samsung UE-22H5000", category, ImmutableSet.of(product2Diagonal, product2Type), productPrice2);

        productInfoRepository.save(product);

        DeliveryInfo deliveryInfo = DeliveryInfo.of("Alexei", "Moscow", "111-11-11");

        Order order = Order.of(deliveryInfo);
        order.addItem(product, 2);

        orderRepository.save(order);
    }
}