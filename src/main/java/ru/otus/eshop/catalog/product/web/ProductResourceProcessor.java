package ru.otus.eshop.catalog.product.web;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ru.otus.eshop.catalog.product.Product;
import ru.otus.eshop.process.cart.web.CartController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProductResourceProcessor implements ResourceProcessor<Resource<Product>> {

    @Override
    public Resource<Product> process(Resource<Product> resource) {
        Product product = resource.getContent();
        resource.add(linkTo(methodOn(CartController.class).addToCart(product.getId(), 1, null)).withRel("add to cart"));
        resource.add(linkTo(methodOn(CartController.class).removeFromCart(product.getId(), null)).withRel("remove from cart"));
        return resource;
    }
}
