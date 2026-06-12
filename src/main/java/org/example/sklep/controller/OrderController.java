package org.example.sklep.controller;

import org.example.sklep.Cart;
import org.example.sklep.CartItem;
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

    @GetMapping("/cart/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId) {
        cartService.addItemById(itemId);
        return "redirect:/order/cart";
    }

    @GetMapping("/cart/remove/{itemId}")
    public String removeItemFromCart(@PathVariable("itemId") Long itemId){
        cartService.removeItemById(itemId);
        return "redirect:/order/cart";
    }
}
// TODO TODO TODO
// naprawic usuwanie przedmiotow (jak w cartView jest
// 1 liczba przedmiotow to nie da sie go calkowicie usunac
