package ru.otus.eshop.web.controller;

import ru.otus.eshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class CartController {
    @Autowired
    private CartService cartService;

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
    public PersistentEntityResource checkout(@PathVariable("id") long cartId, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(cartService.checkout(cartId));
    }

}
