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
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    private Optional<CartItem> getCartItemByItem(Item item){
        return cartItems.stream().filter(cartItem-> cartItem.isEqual(item)).findFirst();
    }

    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        Optional<CartItem> cartItem = getCartItemByItem(item);
        if (cartItem != null) {
            cartItem.get().increaseCounter();
        } else {
            cartItems.add(new CartItem(item));
        }

        recalculatePriceAndCounter();
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
        }

        recalculatePriceAndCounter();
    }

    public void recalculatePriceAndCounter() {
        counter = 0;
        sum = BigDecimal.ZERO;

        for (CartItem cartItem : cartItems) {
            counter += cartItem.getCounter();
            sum = sum.add(cartItem.getPrice());
        }
    }

    public int size(){
        return counter;
    }
}
