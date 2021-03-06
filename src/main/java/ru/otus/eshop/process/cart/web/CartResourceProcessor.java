package ru.otus.eshop.process.cart.web;

import ru.otus.eshop.process.cart.Cart;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class CartResourceProcessor implements ResourceProcessor<Resource<Cart>> {
    @Override
    public Resource<Cart> process(Resource<Cart> resource) {
        Cart cart = resource.getContent();
        resource.add(ControllerLinkBuilder.linkTo(methodOn(CartController.class).checkout(null, null)).withRel("checkout"));
        return resource;
    }
}
