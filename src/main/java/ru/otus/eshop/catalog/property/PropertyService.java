package ru.otus.eshop.catalog.property;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.process.filter.Comparison;
import ru.otus.eshop.process.filter.Filter;
import ru.otus.eshop.process.filter.FilterCondition;
import ru.otus.eshop.process.filter.FilterRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class PropertyService implements IPropertyService {
    private final PropertyRepository propertyRepository;
    private final FilterRepository filterRepository;

    public Property save(Property property) {
        boolean isNew = property.getId() == null;
        property = propertyRepository.save(property);
        if (isNew) {
            Filter filter = filterRepository.getFilterByCategory(property.getCategory());
            filter.addCondition(new FilterCondition(Comparison.eq, property));
            filterRepository.save(filter);
        }
        return property;
    }
}
