package ru.otus.eshop.process.filter;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.otus.eshop.BaseEntity;
import ru.otus.eshop.catalog.category.Category;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.catalog.property.Property;
import ru.otus.eshop.catalog.property.PropertyValue;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Filter extends BaseEntity implements Specification<Product> {
    @OneToOne(fetch = FetchType.LAZY)
    private
    @NonNull
    Category category;

    @ElementCollection
    private Map<Property, FilterCondition> conditions = new HashMap<>();

    public void addCondition(FilterCondition condition) {
        conditions.put(condition.getProperty(), condition);
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Join<Product, PropertyValue> propertyValues = root.join("properties");
        List<Predicate> predicates = buildPredicates(propertyValues, criteriaBuilder);
        predicates.add(criteriaBuilder.equal(root.get("category"), category));
        criteriaQuery.distinct(true);
        return predicates.size() > 1 ? criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])) : predicates.get(0);
    }

    private List<Predicate> buildPredicates(Join<Product, PropertyValue> propertyValues, CriteriaBuilder criteriaBuilder) {
        return conditions.values().stream().filter(c -> c.getCurrentValue() != null).map(c -> buildPredicate(c, propertyValues, criteriaBuilder)).collect(toList());
    }

    public FilterCondition getConditionByProperty(Property property) {
        return conditions.get(property);
    }

    public Predicate buildPredicate(FilterCondition condition, Join<Product, PropertyValue> propertyValues, CriteriaBuilder criteriaBuilder) {
        switch (condition.getComparison()) {
            case eq:
                return buildEqualsPredicateToCriteria(condition, propertyValues, criteriaBuilder);
            case gt:
                break;
            case lt:
                break;
            case ne:
                break;
            case isnull:
                break;
            case in:
                break;
            default:
                return buildEqualsPredicateToCriteria(condition, propertyValues, criteriaBuilder);
        }
        throw new RuntimeException();
    }

    private Predicate buildEqualsPredicateToCriteria(FilterCondition condition, Join<Product, PropertyValue> propertyValues, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.and(criteriaBuilder.equal(propertyValues.get("property"), condition.getProperty()),
                criteriaBuilder.equal(propertyValues.get("value"), condition.getCurrentValue()));
    }
}
