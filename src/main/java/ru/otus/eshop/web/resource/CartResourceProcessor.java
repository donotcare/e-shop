package ru.otus.eshop.web.resource;

import ru.otus.eshop.model.process.Cart;
import ru.otus.eshop.web.controller.CartController;
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
        resource.add(ControllerLinkBuilder.linkTo(methodOn(CartController.class).checkout(1, null)).withRel("checkout"));
        return resource;
    }
}
