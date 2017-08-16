package ru.otus.eshop.catalog.product;

import lombok.RequiredArgsConstructor;
import ru.otus.eshop.catalog.category.Category;

import java.util.List;

@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> findByCategoryFilter(Category category) {
        return null;
    }
}
