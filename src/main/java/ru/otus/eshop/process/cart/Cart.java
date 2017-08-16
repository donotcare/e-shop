package ru.otus.eshop.process.cart;

import lombok.*;
import ru.otus.eshop.BaseEntity;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.system.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Cart extends BaseEntity {
    @OneToOne
    @JoinColumn(nullable = false)
    private @NonNull UserDetails user;

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

    public Map<Product, Integer> getItems() {
        return items.values().stream().collect(Collectors.toMap(CartItem::getProduct, CartItem::getQnt));
    }
}

