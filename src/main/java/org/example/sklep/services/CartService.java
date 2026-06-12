package org.example.sklep.services;

import lombok.Getter;
import org.example.sklep.Cart;
import org.example.sklep.CartItem;
import org.example.sklep.ItemOperation;
import org.example.sklep.model.Item;
import org.example.sklep.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@Getter
public class CartService {
    private final ItemRepository itemRepository;
    private final Cart cart;

    @Autowired
    public CartService(ItemRepository itemRepository, Cart cart) {
        this.itemRepository = itemRepository;
        this.cart = cart;
    }

    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }

    public Optional<Item> findItemById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    public void executeOnItem(Long itemId, ItemOperation operation) {
        Optional<Item> oItem = findItemById(itemId);
        oItem.ifPresent(item -> operation.execute(cart, item));
    }
    // W tej metodzie użycie switch-case zgodnie z instrukcją to zaniechanie zasady Open-closed principle
    // zamiast tego korzystam ze wzorca projektowego Strategia, dzięki czemu mój kod będzie łatwiejszy
    // w utrzymaniu. Dowiedziałem się, że w javie istnieje enum dostosowany do tego wzorca więc korzystam
    // z tego rozwiązania żeby było po "javowemu" i żeby kod był bardziej czytelny.
}

