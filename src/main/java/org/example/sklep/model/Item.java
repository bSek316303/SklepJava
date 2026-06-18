package org.example.sklep.model;

import jakarta.persistence.*;
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
@Table(name="item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;
    private String name;
    private BigDecimal price;
    @Column(name="imgurl")
    private String imgURL;

    public Item(String name, BigDecimal price, String imgURL) {
        this.name = name;
        this.price = price;
        this.imgURL = imgURL;
    } // Przy tworzeniu ten obiekt jeszcze nie ma klucza z @GeneratedValue. Ten klucz pojawia się dopiero przy .save() czyli dodaniu obiektu do bazy.
}
