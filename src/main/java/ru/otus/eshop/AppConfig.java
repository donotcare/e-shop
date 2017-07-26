package ru.otus.eshop;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.eshop.model.catalog.*;
import ru.otus.eshop.model.system.User;
import ru.otus.eshop.model.system.UserRepository;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
public class AppConfig {
    private final ProductRepository productRepository;
    private final ProductInfoRepository productInfoRepository;
    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public AppConfig(ProductRepository productRepository, ProductInfoRepository productInfoRepository, CategoryRepository categoryRepository, PropertyRepository propertyRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.productInfoRepository = productInfoRepository;
        this.categoryRepository = categoryRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public void load() {
        User user = new User();
        user.setName("admin");
        userRepository.save(user);

        Property type = new Property("Тип", PropertyType.STRING);
        propertyRepository.save(type);
        Property diagonal = new Property("Диагональ", PropertyType.STRING);
        propertyRepository.save(diagonal);

        PropertyValue productType = new PropertyValue(type, "ЖК");
        PropertyValue productDiagonal = new PropertyValue(type, "19 \"");

        ProductInfo productInfo = new ProductInfo("LED Телевизор Samsung UE-19H4000", Arrays.asList(productDiagonal, productType));

        productInfoRepository.save(productInfo);

        Product product = new Product();
        product.setInfo(productInfo);
        product.setPrice(new BigDecimal("25000.00"));
        productRepository.save(product);

        PropertyValue product2Type = new PropertyValue(type, "ЖК");
        PropertyValue product2Diagonal = new PropertyValue(type, "17 \"");

        ProductInfo productInfo2 = new ProductInfo("LED Телевизор Samsung UE-22H5000", Arrays.asList(product2Diagonal, product2Type));

        productInfoRepository.save(productInfo2);

        Product product2 = new Product();
        product2.setInfo(productInfo2);
        product2.setPrice(new BigDecimal("40000.00"));

        productRepository.save(product2);
        Category category = new Category("Телевизоры", Arrays.asList(product, product2));
        categoryRepository.save(category);
    }

    @Bean
    CommandLineRunner commandLineRunner(AppConfig dataLoader) {
        return (o) -> dataLoader.load();
    }
}
