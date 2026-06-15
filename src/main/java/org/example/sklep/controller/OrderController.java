package org.example.sklep.controller;

import org.example.sklep.Cart;
import org.example.sklep.CartItem;
import org.example.sklep.ItemOperation;
import org.example.sklep.dto.OrderDto;
import org.example.sklep.model.Item;
import org.example.sklep.services.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final CartService cartService;

    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String showCart() {
        return "cartView";
    }

    @ModelAttribute("cart")
    public Cart cartInModel() { return cartService.getCart();}

    @GetMapping("/cart/increase/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId) {
        cartService.executeOnItem(itemId, ItemOperation.ADD_OR_INCREASE);
        return "redirect:/order/cart";
    }

    @GetMapping("/cart/decrease/{itemId}")
    public String decreaseItemCount(@PathVariable("itemId") Long itemId){
        cartService.executeOnItem(itemId, ItemOperation.DECREASE);
        return "redirect:/order/cart";
    }

    @GetMapping("cart/remove/{itemId}")
    public String removeItemFromCart(@PathVariable("itemId") Long itemId){
        cartService.executeOnItem(itemId, ItemOperation.REMOVE);
        return "redirect:/order/cart";
    }

    @GetMapping("/summary")
    public String showSummary() { return "summary"; }

    @PostMapping("/saveorder")
    public String saveOrder(OrderDto orderDto) {
        return "redirect:/";
    }
}