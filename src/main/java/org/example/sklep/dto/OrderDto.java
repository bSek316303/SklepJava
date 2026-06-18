package org.example.sklep.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    String firstName;
    String lastName;
    String address;
    String postCode;
    String city;
}
