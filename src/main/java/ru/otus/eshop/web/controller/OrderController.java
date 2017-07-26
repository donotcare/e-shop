package ru.otus.eshop.web.controller;

import ru.otus.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping(value = "/orders/{id}/pay", method = RequestMethod.POST, produces = "application/hal+json")
    public PersistentEntityResource checkout(@PathVariable("id") long orderId, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(orderService.pay(orderId));
    }

    @ResponseBody
    @RequestMapping(value = "/orders/{id}/cancel", method = RequestMethod.POST, produces = "application/hal+json")
    public PersistentEntityResource cancel(@PathVariable("id") long orderId, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(orderService.cancel(orderId));
    }
}
