package org.example.sklep;

import lombok.Getter;
import org.example.sklep.model.Item;

import java.math.BigDecimal;

@Getter
public class CartItem {
    private final Item item;
    private int counter;
    private BigDecimal price;

    public CartItem(Item item) {
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }

    public void increaseCounter() {
        counter++;
        recalculate();
    }

    public void decreaseCounter() {
        counter--;
        recalculate();
    }

    private void recalculate() {
        price = item.getPrice().multiply(BigDecimal.valueOf(counter));
    }
}
