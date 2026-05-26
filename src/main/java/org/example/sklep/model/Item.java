package org.example.sklep.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private BigDecimal price;
    private String imgURL;

    public Item(String name, BigDecimal price, String imgURL) {
        this.name = name;
        this.price = price;
        this.imgURL = imgURL;
    } // Przy tworzeniu ten obiekt jeszcze nie ma klucza z @GeneratedValue. Ten klucz pojawia się dopiero przy .save() czyli dodaniu obiektu do bazy.
}
