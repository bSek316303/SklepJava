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
    // Co ciekawe to Spring boot samemu tworzy implementacje interface'u ItemRepository w postaci klasy.

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
    public String addItemToCart(@PathVariable("itemId") Long itemId, Model model, HttpSession session){
        List<Item> cart = (List<Item>) session.getAttribute("cart");
        if(cart == null) cart = new ArrayList<>();

        Optional<Item> oItem = itemRepository.findById(itemId); // Optional pozwala uniknąć NullPointerException, ponieważ może zawierać wartość lub być puste

        if(oItem.isPresent()){
            Item item = oItem.get();
            cart.add(item);
            session.setAttribute("cart", cart);
        }
        model.addAttribute("items", itemRepository.findAll());
        return "redirect:/";
        // return "redirect:/" zamiast return "home" naprawia błąd. Błąd był taki, że po dodaniu do koszyka
        // zostawaliśmy na ip/add/(id) zamiast zostać przekierowani na ip. Zmiana "home" na redirect:/ jest zgodna
        // ze wzorcem projektowym PRG -> Post - redirect - get czyli jak używamy metody Post to później wykorzystujemy
        // mechanizm redirect na metodę get, która dopiero zwraca stronę. Dzięki temu unikamy problemu z odświeżaniem strony,
        // który powodowałby ponowne wysłanie danych do serwera i dodanie tego samego produktu do koszyka.
    }
}
