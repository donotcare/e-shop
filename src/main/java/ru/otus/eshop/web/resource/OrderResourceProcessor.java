package ru.otus.eshop.web.resource;

import ru.otus.eshop.model.process.Order;
import ru.otus.eshop.model.process.OrderStatus;
import ru.otus.eshop.web.controller.OrderController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class OrderResourceProcessor implements ResourceProcessor<Resource<Order>> {
    @Override
    public Resource<Order> process(Resource<Order> resource) {
        Order order = resource.getContent();
        if(order.getStatus() == OrderStatus.NEW) {
            resource.add(ControllerLinkBuilder.linkTo(methodOn(OrderController.class).checkout(order.getId(), null)).withRel("pay"));
        }
        return resource;
    }
}
