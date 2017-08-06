package ru.otus.eshop.web.controller;

import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;
import ru.otus.eshop.model.process.delivery.DeliveryInfo;
import ru.otus.eshop.service.ICartService;

@RepositoryRestController
public class CartController {
    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @ResponseBody
    @RequestMapping(value = "/carts/{id}/product/{productId}/add/{qnt}", method = RequestMethod.PUT, produces = "application/hal+json")
    public PersistentEntityResource addToCart(@PathVariable("id") long cartId, @PathVariable("productId") long productId, @PathVariable("qnt") int qnt,
                                              PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(cartService.addToCart(cartId, productId, qnt));
    }

    @ResponseBody
    @RequestMapping(value = "/carts/{id}/product/{productId}/remove", method = RequestMethod.PUT, produces = "application/hal+json")
    public PersistentEntityResource removeFromCart(@PathVariable("id") long cartId, @PathVariable("productId") long productId, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(cartService.removeFromCart(cartId, productId));
    }

    @ResponseBody
    @RequestMapping(value = "/carts/{id}/checkout", method = RequestMethod.POST, produces = "application/hal+json")
    public PersistentEntityResource checkout(@PathVariable("id") long cartId, @RequestBody DeliveryInfo deliveryInfo, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(cartService.checkout(cartId, deliveryInfo));
    }
}
