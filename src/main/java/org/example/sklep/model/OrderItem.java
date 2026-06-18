package org.example.sklep.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@Getter
@Data
@Table(name = "orderitems")
public class OrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitemid")
    private Long orderItemId;
    @Column(name = "orderid")
    private Long orderId;
    @Column(name = "itemid")
    private Long itemId;
    private int amount;

    public OrderItem(Long itemId, Long orderId, int amount) {
        this.itemId = itemId;
        this.orderId = orderId;
        this.amount = amount;
    }
}
