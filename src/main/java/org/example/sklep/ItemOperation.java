package org.example.sklep;

import org.example.sklep.model.Item;

import java.util.function.BiConsumer;

public enum ItemOperation {
    ADD_OR_INCREASE(Cart::addOrIncreaseItem),
    DECREASE(Cart::decreaseItem),
    REMOVE(Cart::removeItem);

    // Każdy enum przechowuje referencję do konkretnej metody z klasy Cart
    private final BiConsumer<Cart, Item> action;

    ItemOperation(BiConsumer<Cart, Item> action) {
        this.action = action;
    }

    public void execute(Cart cart, Item item) {
        this.action.accept(cart, item);
    }
}
