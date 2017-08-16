package ru.otus.eshop.process.cart;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query("select cart from Cart cart where cart.user.username = ?#{ principal?.username }")
    Cart getCurrentUserCart();
}
