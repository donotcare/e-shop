package ru.otus.eshop.catalog.product;

import org.springframework.data.rest.core.config.Projection;

@Projection(types = Product.class)
interface ProductExcerptProjection {
    String getName();
}
