package org.example.sklep;

import org.example.sklep.model.Item;
import org.example.sklep.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DbInit implements CommandLineRunner{
    private final ItemRepository itemRepository;

    @Autowired
    public DbInit(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception{
        if(itemRepository.count() == 0) {
            itemRepository.saveAll(List.of(
                    (new Item("Piłka do koszykówki", new BigDecimal("149.99")
                            , "https://sklepkoszykarski.pl/userdata/public/gfx/7438/Pilka-do-koszykowki-Wilson-NBA-Authentic-InOut.jpg")),
                    new Item("Koszulka lakers", new BigDecimal("299.99")
                            , "https://www.intersport.pl/media/front_thumbnails/webp650px/catalog_product_k_o_koszulka-do-koszykowki-meska-nike-los-angeles-lakers-icon-edition-2022-23-dn2009-287353kw__h__650.webp"),
                    new Item("Pompka elektryczna", new BigDecimal("99.99")
                            , "https://m.media-amazon.com/images/I/51xJsY+3uIL._AC_SL1500_.jpg")));
        }
    }
}
