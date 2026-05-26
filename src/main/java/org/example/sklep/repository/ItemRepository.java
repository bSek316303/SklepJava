package org.example.sklep.repository;

import org.example.sklep.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // Spring sam wygeneruje SQL: SELECT * FROM item WHERE name = ?
    List<Item> findByName(String name);

    // Spring sam wygeneruje SQL: SELECT * FROM item WHERE price < ?
    List<Item> findByPriceLessThan(BigDecimal maxPrice);
}
