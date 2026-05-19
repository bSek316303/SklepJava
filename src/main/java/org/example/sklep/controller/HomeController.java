package org.example.sklep.controller;

import model.Item;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    static List<Item> items = new ArrayList<>();
    static {
        items.add(new Item("Piłka do koszykówki", new BigDecimal("149.99")
                , "https://sklepkoszykarski.pl/userdata/public/gfx/7438/Pilka-do-koszykowki-Wilson-NBA-Authentic-InOut.jpg"));
        items.add(new Item("Koszulka lakers", new BigDecimal("299.99")
                , "https://www.intersport.pl/media/front_thumbnails/webp650px/catalog_product_k_o_koszulka-do-koszykowki-meska-nike-los-angeles-lakers-icon-edition-2022-23-dn2009-287353kw__h__650.webp"));
        items.add(new Item("Pompka elektryczna", new BigDecimal("99.99")
                , "https://m.media-amazon.com/images/I/51xJsY+3uIL._AC_SL1500_.jpg"));
    }

    @GetMapping("/")
    //@ResponseBody
    public String home(Model model) {
        model.addAttribute("items",items);
        return "home";
    }
}
