package ru.otus.eshop.model.catalog;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<Product> {
}
