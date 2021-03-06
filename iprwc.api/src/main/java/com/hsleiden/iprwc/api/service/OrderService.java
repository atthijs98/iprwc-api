package com.hsleiden.iprwc.api.service;

import com.hsleiden.iprwc.api.model.Order;
import com.hsleiden.iprwc.api.model.dto.OrderCreateDto;

public interface OrderService {
    Iterable<Order> getAllOrders();
    Iterable<Order> getUserOrders();
    Order createOrder(OrderCreateDto orderCreateDto);
    void updateOrder(Order order);
}
