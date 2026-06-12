package org.example.sklep.controller;

import org.example.sklep.Cart;
import org.example.sklep.model.Item;
import org.example.sklep.repository.ItemRepository;
import org.example.sklep.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeController {
    CartService cartService;

    @Autowired
    public HomeController(CartService cartService, Cart cart) {
        this.cartService = cartService;
    }

    @ModelAttribute("cart")
    public Cart cartInModel() {
        return cartService.getCart();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("items", cartService.getAllItems());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") Long itemId) {
        cartService.addItemById(itemId);
        return "redirect:/";
    }
}
