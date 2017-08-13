package ru.otus.eshop.model.process.filter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.otus.eshop.model.catalog.Category;
import ru.otus.eshop.model.catalog.Product;
import ru.otus.eshop.model.catalog.PropertyValue;

import javax.persistence.criteria.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class RealFilter implements Specification<Product> {
    private final Category category;
    private final List<FilterCondition> conditions;

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Join<Product, PropertyValue> propertyValues = root.join("properties");
        List<Predicate> predicates = buildPredicates(propertyValues, criteriaBuilder);
        predicates.add(criteriaBuilder.equal(root.get("category"), category));
        criteriaQuery.distinct(true);
        return predicates.size() > 1 ? criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])) : predicates.get(0);
    }

    private List<Predicate> buildPredicates(Join<Product, PropertyValue> propertyValues, CriteriaBuilder criteriaBuilder) {
        return conditions.stream().filter(c -> c.getCurrentValue() != null).map(c -> buildPredicate(c, propertyValues, criteriaBuilder)).collect(toList());
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
