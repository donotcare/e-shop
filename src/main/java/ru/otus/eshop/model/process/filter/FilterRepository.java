package ru.otus.eshop.model.process.filter;

import org.springframework.data.repository.CrudRepository;
import ru.otus.eshop.model.catalog.Category;

public interface FilterRepository extends CrudRepository<Filter, Long> {

    Filter getFilterByCategory(Category category);
}
