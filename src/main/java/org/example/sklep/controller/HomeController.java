package org.example.sklep.controller;

import jakarta.servlet.http.HttpSession;
import org.example.sklep.model.Item;
import org.example.sklep.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    private final ItemRepository itemRepository;

    @Autowired
    public HomeController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/")
    //@ResponseBody
    public String home(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }

    @GetMapping("/add/{itemId}")
    public String addItemToCart(@PathVariable("itemId") long itemId, Model model, HttpSession httpSession){
        List<Item> cart = (List<Item>) httpSession.getAttribute("cart");
        if(cart == null){
            cart = new ArrayList<>();
        }
        Optional<Item> oItem = itemRepository.findById(itemId);
        if(oItem.isPresent()){
            Item item = oItem.get();
            cart.add(item);
            httpSession.setAttribute("cart", cart);
        }
        model.addAttribute("items", itemRepository.findAll());
        return "home";
    }
}
