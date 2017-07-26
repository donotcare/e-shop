package ru.otus.eshop.model.catalog;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductInfoRepository extends PagingAndSortingRepository<ProductInfo, Long> {
}
