package org.example.sklep.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class OrderItem {
    @Id
    @GeneratedValue
    private Long orderItemId;
    private Long orderId;
    private Long itemId;
    private int amount;

    public OrderItem(Long itemId, Long orderId, int amount) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
