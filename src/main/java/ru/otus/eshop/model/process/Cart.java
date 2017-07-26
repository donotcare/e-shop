package ru.otus.eshop.model.process;

import ru.otus.eshop.model.BaseEntity;
import ru.otus.eshop.model.catalog.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Cart extends BaseEntity {
    @ElementCollection
    private Map<Product, CartItem> items;

    public void addCartItem(CartItem item) {
        items.put(item.getProduct(), item);
    }

    public void removeCartItem(CartItem item) {
        items.remove(item.getProduct());
    }

    public void clear() {
        items.clear();
    }
}

