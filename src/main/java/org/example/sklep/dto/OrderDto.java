package org.example.sklep.dto;

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
