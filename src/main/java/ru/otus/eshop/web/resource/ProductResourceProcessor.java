package ru.otus.eshop.web.resource;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;
import ru.otus.eshop.model.catalog.ProductDescription;
import ru.otus.eshop.web.controller.CartController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ProductResourceProcessor implements ResourceProcessor<Resource<ProductDescription>> {

    @Override
    public Resource<ProductDescription> process(Resource<ProductDescription> resource) {
        ProductDescription product = resource.getContent();
        resource.add(linkTo(methodOn(CartController.class).addToCart(1, product.getId(), 1, null)).withRel("add to cart"));
        resource.add(linkTo(methodOn(CartController.class).removeFromCart(1, product.getId(), null)).withRel("remove from cart"));
        return resource;
    }
}
