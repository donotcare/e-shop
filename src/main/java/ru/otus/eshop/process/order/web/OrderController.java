package ru.otus.eshop.process.order.web;

import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.eshop.process.order.IOrderService;

@RepositoryRestController
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseBody
    @RequestMapping(value = "/orders/{id}/pay", method = RequestMethod.POST, produces = "application/hal+json")
    public PersistentEntityResource pay(@PathVariable("id") long orderId, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(orderService.pay(orderId));
    }

    @ResponseBody
    @RequestMapping(value = "/orders/{id}/cancel", method = RequestMethod.POST, produces = "application/hal+json")
    public PersistentEntityResource cancel(@PathVariable("id") long orderId, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(orderService.cancel(orderId));
    }
}
