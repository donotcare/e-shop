package ru.otus.eshop;

import com.google.common.collect.ImmutableSet;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.otus.eshop.model.catalog.*;
import ru.otus.eshop.model.system.Role;
import ru.otus.eshop.model.system.UserDetails;
import ru.otus.eshop.model.system.UserRepository;
import ru.otus.eshop.security.SecurityUtil;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class AppConfig {
    private final ProductDescriptionRepository productInfoRepository;
    private final CategoryRepository categoryRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    public AppConfig(ProductDescriptionRepository productInfoRepository, CategoryRepository categoryRepository, PropertyRepository propertyRepository, UserRepository userRepository) {
        this.productInfoRepository = productInfoRepository;
        this.categoryRepository = categoryRepository;
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public void load() {
        UserDetails user = new UserDetails("user", Collections.singletonList(Role.ADMIN));
        user.setPassword("user");
        userRepository.save(user);

        SecurityUtil.runAs("user", "user", "ROLE_ADMIN");

        SecurityContextHolder.getContext().getAuthentication();

        Property type = new Property("Тип", PropertyType.STRING);
        propertyRepository.save(type);
        Property diagonal = new Property("Диагональ", PropertyType.STRING);
        propertyRepository.save(diagonal);

        PropertyValue productType = new PropertyValue(type, "ЖК");
        PropertyValue productDiagonal = new PropertyValue(type, "19 \"");
        ProductPrice productPrice = ProductPrice.of(new BigDecimal("25000.00"));
        ProductDescription productDescription = new ProductDescription("LED Телевизор Samsung UE-19H4000", ImmutableSet.of(productDiagonal, productType), productPrice);

        productInfoRepository.save(productDescription);

        PropertyValue product2Type = new PropertyValue(type, "ЖК");
        PropertyValue product2Diagonal = new PropertyValue(type, "17 \"");
        ProductPrice productPrice2 = ProductPrice.of(new BigDecimal("40000.00"));
        ProductDescription productDescription2 = new ProductDescription("LED Телевизор Samsung UE-22H5000", ImmutableSet.of(product2Diagonal, product2Type), productPrice2);

        productInfoRepository.save(productDescription2);

        Category category = new Category("Телевизоры", Arrays.asList(productDescription, productDescription2));
        categoryRepository.save(category);
    }

    @Bean
    CommandLineRunner commandLineRunner(AppConfig dataLoader) {
        return (o) -> dataLoader.load();
    }

}
