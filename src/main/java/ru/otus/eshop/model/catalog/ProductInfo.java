package ru.otus.eshop.model.catalog;

import lombok.*;
import org.hibernate.annotations.Cascade;
import ru.otus.eshop.model.NamedEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
public class ProductInfo extends NamedEntity {
    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private @NonNull List<PropertyValue> properties;

    public ProductInfo(String name, List<PropertyValue> properties) {
        super(name);
        this.properties = properties;
    }

    ProductInfo() {
        super(null);
    }
}
