package org.example.sklep.services;

import org.example.sklep.Cart;
import org.example.sklep.dto.OrderDto;
import org.example.sklep.mapper.OrderMapper;
import org.example.sklep.model.Order;
import org.example.sklep.repository.OrderItemRepository;
import org.example.sklep.repository.OrderRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final Cart cart;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderService(Cart cart, OrderRepository orderRepository, OrderItemRepository orderItemRepository){
        this.cart = cart;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Transactional
    public void saveOrder(OrderDto orderDto){
        Order order = orderRepository.save(OrderMapper.mapToOrder(orderDto));
        orderItemRepository.saveAll(OrderMapper.mapToOrderItemList(cart, order));
        cart.cleanCart();
    }
}
