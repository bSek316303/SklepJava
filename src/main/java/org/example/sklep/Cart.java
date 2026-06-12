package org.example.sklep;

import lombok.Getter;
import org.example.sklep.model.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();

    private Optional<CartItem> getCartItemByItem(Item item){
        return cartItems.stream().
                filter(cartItem-> cartItem.isEqual(item)).findFirst();
    }

    public Optional<CartItem> findCartItemById(long itemId) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getItem() != null && cartItem.getItem().getId() == itemId)
                .findFirst();
    }

    public void addItemById(long itemId){
        Optional<CartItem> cartItem = findCartItemById(itemId);
        if(cartItem.isPresent()){
            cartItem.get().increaseCounter();
        }
    }

    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        Optional<CartItem> cartItem = getCartItemByItem(item);
        if (cartItem.isPresent()) {
            cartItem.get().increaseCounter();
        } else {
            cartItems.add(new CartItem(item));
        }
    }

    public void removeItem(Item item) {
        if (item == null) {
            return;
        }

        Optional<CartItem> cartItem = getCartItemByItem(item);
        if (cartItem == null) {
            return;
        }

        if (cartItem.get().getCounter() > 1) {
            cartItem.get().decreaseCounter();
        } else {
            cartItems.remove(cartItem);
            if (cartItem.get().getCounter() <= 0){
                cartItems.remove(cartItem.get());
            }
        }
    }

    public int size() {
        return cartItems.stream()
                .mapToInt(CartItem::getCounter)
                .sum();
    }

    public BigDecimal price() {
        return cartItems.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getCounter())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
