package org.example.sklep.services;

import lombok.Getter;
import org.example.sklep.Cart;
import org.example.sklep.model.Item;
import org.example.sklep.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

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

    public void addItemById(Long itemId){
        Optional<Item> oItem = findItemById(itemId);
        oItem.ifPresent(cart::addItem);
    }

    public void removeItemById(Long itemId){
        Optional<Item> oItem = findItemById(itemId);
        oItem.ifPresent(cart::removeItem);
    }
}

