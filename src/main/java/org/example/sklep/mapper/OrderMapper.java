package org.example.sklep.mapper;

import org.example.sklep.Cart;
import org.example.sklep.CartItem;
import org.example.sklep.dto.OrderDto;
import org.example.sklep.model.Order;
import org.example.sklep.model.OrderItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order mapToOrder(OrderDto orderDto){
        return Order.builder()
                .firstName(orderDto.getFirstName())
                .lastName(orderDto.getLastName())
                .address(orderDto.getAddress())
                .postCode(orderDto.getPostCode())
                .city(orderDto.getCity())
                .created(LocalDateTime.now())
                .build();
    }

    public static List<OrderItem> mapToOrderItemList(Cart cart, Order order){
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItem ci : cart.getCartItems()){
            orderItems.add(new OrderItem(ci.getItem().getId(), order.getOrderId(), ci.getCounter()));
        }
        return orderItems;
    }
}
