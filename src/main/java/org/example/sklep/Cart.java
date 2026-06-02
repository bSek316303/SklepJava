package org.example.sklep;

import lombok.Getter;
import org.example.sklep.model.Item;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
public class Cart {
    private final List<CartItem> cartItems = new ArrayList<>();
    private int counter = 0;
    private BigDecimal sum = BigDecimal.ZERO;

    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        CartItem cartItem = findCartItemByItemId(item.getId());
        if (cartItem != null) {
            cartItem.increaseCounter();
        } else {
            cartItems.add(new CartItem(item));
        }

        recalculatePriceAndCounter();
    }

    public void removeItem(Item item) {
        if (item == null) {
            return;
        }

        CartItem cartItem = findCartItemByItemId(item.getId());
        if (cartItem == null) {
            return;
        }

        if (cartItem.getCounter() > 1) {
            cartItem.decreaseCounter();
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

    private CartItem findCartItemByItemId(long itemId) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getItem().getId() == itemId) {
                return cartItem;
            }
        }

        return null;
    }

    public int size(){
        System.out.println(counter);
        return counter;
    }
}
