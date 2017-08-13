package ru.otus.eshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.eshop.model.catalog.Property;
import ru.otus.eshop.model.catalog.PropertyRepository;
import ru.otus.eshop.model.process.filter.Comparison;
import ru.otus.eshop.model.process.filter.Filter;
import ru.otus.eshop.model.process.filter.FilterCondition;
import ru.otus.eshop.model.process.filter.FilterRepository;

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
