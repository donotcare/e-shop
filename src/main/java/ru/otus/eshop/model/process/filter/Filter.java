package ru.otus.eshop.model.process.filter;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.otus.eshop.model.BaseEntity;
import ru.otus.eshop.model.catalog.Category;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Filter extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    private @NonNull Category category;
    @ElementCollection
    private List<FilterCondition> conditions = new ArrayList<>();

    public RealFilter getRealFilter() {
        return new RealFilter(category, conditions);
    }

    public void addCondition(FilterCondition condition) {
        conditions.add(condition);
    }
}
