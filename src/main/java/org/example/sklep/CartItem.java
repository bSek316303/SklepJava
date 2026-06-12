package org.example.sklep;

import lombok.Getter;
import org.example.sklep.model.Item;

import java.math.BigDecimal;

@Getter
public class CartItem {
    private final Item item;
    private int counter;
    private final BigDecimal price;

    public CartItem(Item item) {
        this.item = item;
        this.counter = 1;
        this.price = item.getPrice();
    }

    public boolean isEqual(Item item){
        if(item == null || this.getItem() == null){
            return false;
        }
        return this.getItem().getId() == item.getId();
    }

    public void increaseCounter() {
        counter++;
    }

    public void decreaseCounter() {
        counter--;
    }
    public BigDecimal combinedPrice() { return price.multiply(BigDecimal.valueOf(counter)); }
}
