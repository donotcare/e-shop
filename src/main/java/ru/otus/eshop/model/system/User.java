package ru.otus.eshop.model.system;

import ru.otus.eshop.model.process.Cart;
import ru.otus.eshop.model.process.Order;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart = new Cart();
    @OneToMany
    private List<Order> orders;
}
