package ru.otus.eshop.catalog.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.process.filter.Filter;
import ru.otus.eshop.process.filter.FilterRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final FilterRepository filterRepository;

    public Category save(Category category) {
        boolean isNew = category.getId() == null;
        category = categoryRepository.save(category);
        if (isNew) {
            Filter filter = new Filter(category);
            filterRepository.save(filter);
        }
        return category;
    }
}
