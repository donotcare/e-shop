package ru.otus.eshop.process.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = OrderExcerptProjection.class)
public interface OrderRepository extends CrudRepository<Order, Long> {
}
