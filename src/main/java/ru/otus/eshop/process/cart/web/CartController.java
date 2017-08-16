package ru.otus.eshop.process.cart.web;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.web.bind.annotation.*;
import ru.otus.eshop.process.order.delivery.DeliveryInfo;
import ru.otus.eshop.process.cart.ICartService;

@BasePathAwareController
public class CartController {
    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @ResponseBody
    @RequestMapping(value = "/cart/add/{productId}/qnt/{qnt}", method = RequestMethod.PUT, produces = "application/hal+json")
    public PersistentEntityResource addToCart(@PathVariable("productId") long productId, @PathVariable("qnt") int qnt, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(cartService.addToCart(productId, qnt));
    }

    @ResponseBody
    @RequestMapping(value = "/cart/remove/{productId}", method = RequestMethod.DELETE, produces = "application/hal+json")
    public PersistentEntityResource removeFromCart(@PathVariable("productId") long productId, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(cartService.removeFromCart(productId));
    }

    @ResponseBody
    @RequestMapping(value = "/cart/checkout", method = RequestMethod.POST, produces = "application/hal+json")
    public PersistentEntityResource checkout(@RequestBody DeliveryInfo deliveryInfo, PersistentEntityResourceAssembler assembler) {
        return assembler.toFullResource(cartService.checkout(deliveryInfo));
    }
}
