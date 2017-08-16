package ru.otus.eshop.process.filter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.otus.eshop.catalog.category.Category;

public interface FilterRepository extends CrudRepository<Filter, Long> {
    Filter getFilterByCategory(Category category);

    @RestResource(exported = false)
    @Override
    <S extends Filter> S save(S entity);

    @RestResource(exported = false)
    @Override
    <S extends Filter> Iterable<S> save(Iterable<S> entities);

    @RestResource(exported = false)
    @Override
    void delete(Long aLong);

    @RestResource(exported = false)
    @Override
    void delete(Filter entity);

    @RestResource(exported = false)
    @Override
    void delete(Iterable<? extends Filter> entities);

    @RestResource(exported = false)
    @Override
    void deleteAll();
}
