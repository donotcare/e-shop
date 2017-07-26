package ru.otus.eshop.model.process;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.otus.eshop.model.BaseEntity;
import ru.otus.eshop.model.catalog.Product;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
public class Cart extends BaseEntity {
    @ElementCollection
    private Map<Product, CartItem> items = new HashMap<>();

    public void addCartItem(CartItem item) {
        items.put(item.getProduct(), item);
    }

    public void removeCartItem(CartItem item) {
        items.remove(item.getProduct());
    }

    public void clear() {
        items.clear();
    }

    public Collection<CartItem> getItems() {
        return Collections.unmodifiableCollection(items.values());
    }
}

