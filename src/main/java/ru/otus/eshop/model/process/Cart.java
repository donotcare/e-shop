package ru.otus.eshop.model.process;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.otus.eshop.model.BaseEntity;
import ru.otus.eshop.model.catalog.ProductDescription;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Getter
@Entity
public class Cart extends BaseEntity {
    @ElementCollection
    private Map<ProductDescription, CartItem> items = new HashMap<>();

    public void addCartItem(CartItem item) {
        items.put(item.getProduct(), item);
    }

    public void removeCartItem(CartItem item) {
        items.remove(item.getProduct());
    }

    public void clear() {
        items.clear();
    }

    public Map<ProductDescription, Integer> getItems() {
        return items.values().stream().collect(Collectors.toMap(CartItem::getProduct, CartItem::getQnt));
    }
}

