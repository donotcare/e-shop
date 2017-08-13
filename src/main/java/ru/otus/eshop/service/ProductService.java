package ru.otus.eshop.service;

import lombok.RequiredArgsConstructor;
import ru.otus.eshop.model.catalog.Category;
import ru.otus.eshop.model.catalog.Product;
import ru.otus.eshop.model.catalog.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public List<Product> findByCategoryFilter(Category category) {
        return null;
    }
}
